package com.jasminelawrence.popularmoviesstage1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter mMovieAdapter;
    private List<Movie> mMovieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieAdapter = new MovieAdapter(this, mMovieList);

        // Get a reference to the ListView, and attach this adapter to it.
        GridView movieListView = findViewById(R.id.movies_gridview);
        movieListView.setAdapter(mMovieAdapter);


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Movie selectedMovie = mMovieAdapter.getItem(position);

                Toast.makeText(MainActivity.this, selectedMovie.getOriginalTitle(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                i.putExtra("ORIGINAL_NAME", selectedMovie.getOriginalTitle());
                i.putExtra("PLOT", selectedMovie.getPlotSynopsis());
                i.putExtra("POSTER_IMAGE", selectedMovie.getPosterImage());
                i.putExtra("RELEASE_DATE", selectedMovie.getReleaseDate());
                i.putExtra("USER_RATING", String.valueOf(selectedMovie.getUserRating()));

                startActivity(i);


                // TO DO update to open details view
              /*  // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(selectedMovie.getOriginalTitle());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);*/


            }
        });

        movieSearch();

    }


    private void movieSearch() {

       URL movieURL = NetworkUtils.buildUrl();
       new MovieSearchTask().execute(movieURL);

    }

    public class MovieSearchTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String movieSearchResults = null;
            try {
                movieSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieSearchResults;
        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String movieSearchResults) {
            if (movieSearchResults != null && !movieSearchResults.equals("")) {
                Toast.makeText(getBaseContext(), "Completed successfully", Toast.LENGTH_SHORT).show();
                String test = movieSearchResults;
                //make an object and parse json
                mMovieList = extractFeatureFromJson(movieSearchResults);

            }
        }
    }


    private static List<Movie> extractFeatureFromJson(String MoviesJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(MoviesJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Articles to
        List<Movie> movieList = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(MoviesJSON);

            // Extract the JSONArray associated with the key called "reponse",
            // which represents a list of items (or Articles).
            JSONObject response = baseJsonResponse.getJSONObject("response");

            JSONArray articlesArray = response.getJSONArray("results");

            // For each Article in the ArticleArray, create an {@link Article} object
            for (int i = 0; i < articlesArray.length(); i++) {

                // Get a single Article at position i within the list of Articles
                JSONObject currentArticle = articlesArray.getJSONObject(i);

                // For a given Article, extract the JSONObject associated with the
                // key called "results", which represents a list of all properties
                // for that Article.
                // Extract the value for the key called "place"
                JSONObject fields = currentArticle.getJSONObject("fields");
                String title = fields.getString("headline");

                // Extract the value for the key called "place"
                String section = currentArticle.getString("sectionName");

                // Extract the value for the key called "place"
                String published = currentArticle.getString("webPublicationDate");

                // Extract the value for the key called "place"
                String url = currentArticle.getString("webUrl");


                // Create a new {@link Article} object with the title, author, date published,
                // and url from the JSON response.
                Movie movie  =  new Movie("Cupcake", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description.test description.test description. test description", 1.776, "test release");


                // Add the new {@link Article} to the list of Articles.
                movieList.add(movie);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("MainActivity", "Problem parsing the Article JSON results", e);
        }

        // Return the list of Articles
        return movieList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int option_id = item.getItemId();

        switch (option_id) {

            case R.id.popular_sort:
                //popular

                Toast.makeText(this, "Sorting by popularity", Toast.LENGTH_SHORT).show();
                movieSearch();
                return true;

            case R.id.top_rated_sort:
                //user rating
                Toast.makeText(this, "Sorting by user rating", Toast.LENGTH_SHORT).show();
                movieSearch();
                return true;



        }

        return super.onOptionsItemSelected(item);

    }


}
