package com.jasminelawrence.popularmoviesstage2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.movies_gridview)
    GridView movieListView;

    private MovieAdapter mMovieAdapter;
    private ArrayList<Movie> mMovieList;
    private String filter;
    private boolean isConnected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mMovieList = new ArrayList<>();


        // Get a reference to the GridView, and attach this adapter to it.
        //default filter
        filter = getResources().getString(R.string.popular_filter);

        if (isConnected) {

            movieSearch(filter);
        }

        // Set an item click listener on the GridView to open the details page when a movie is selected
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current movie that was clicked on
                Movie selectedMovie = mMovieAdapter.getItem(position);

                Intent i = new Intent(getApplicationContext(), MovieDetails.class);
                i.putExtra("theMovieDetails", selectedMovie); // using the (String name, Parcelable value) overload!
                startActivity(i); // dataToSend is now passed to the new Activity

            }
        });

    }

    private void movieSearch(String filter) {


            URL movieURL = NetworkUtils.buildUrl(filter);
            new MovieSearchTask().execute(movieURL);

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
                filter = getResources().getString(R.string.popular_filter);
                movieSearch(filter);
                return true;

            case R.id.top_rated_sort:
                //user rating
                filter = getResources().getString(R.string.rating_filter);
                movieSearch(filter);
                return true;

            case R.id.favorite_sort:
                //user rating
                filter = getResources().getString(R.string.favorite_filter);
                //TODO read from DB

                Toast.makeText(MainActivity.this,
                        "Showing favorites", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private class MovieSearchTask extends AsyncTask<URL, Void, Void> {

        @Override
        protected Void doInBackground(URL... params) {
            URL searchUrl = params[0];
            String movieSearchResults;
            try {
                movieSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                mMovieList = extractFeatureFromJson(movieSearchResults);
                //TODO put movies in the DB

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

            mMovieAdapter = new MovieAdapter(MainActivity.this, mMovieList);
            movieListView.setAdapter(mMovieAdapter);

        }

    }

    private static ArrayList<Movie> extractFeatureFromJson(String MoviesJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(MoviesJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding movies
        ArrayList<Movie> movieList = new ArrayList<>();


        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(MoviesJSON);

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of items (or movies).

            JSONArray moviesArray = baseJsonResponse.getJSONArray("results");

            // For each Movie in the MovieArray, create an Movie object
            for (int i = 0; i < moviesArray.length(); i++) {

                // Get a single movie at position i within the list of Movies
                JSONObject currentMovie = moviesArray.getJSONObject(i);

                //get the relevant information about the movie
                String original_title = currentMovie.getString("original_title");

                String plot_synopsis = currentMovie.getString("overview");

                //TODO format the date
                String release_date = currentMovie.getString("release_date");

                String poster_path = currentMovie.getString("poster_path");
                String poster_url = "http://image.tmdb.org/t/p/w185/" + poster_path;

                double user_rating = currentMovie.getDouble("vote_average");
                int id = currentMovie.getInt("id");

                // Create a new Movie object with the infromation
                Movie movie = new Movie(id, original_title, poster_url, plot_synopsis, user_rating, release_date, null, null);


                // Add the new movie to the list of movies
                movieList.add(movie);

            }

        } catch (JSONException e) {

            Log.e("MainActivity", "Problem parsing the Article JSON results", e);

        }

        // Return the list of movies
        return movieList;
    }





}
