package com.jasminelawrence.popularmoviesstage1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter mMovieAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //sample move data

        Movie[] movies = {
                new Movie("Cupcake", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","test description.test description.test description. test description", 1.776, "test release"),
                new Movie("Donut", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","test description", 1.776, "test release"),
                new Movie("Eclair", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","test description", 1.776, "test release"),
                new Movie("Froyo", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","test description", 1.776, "test release"),
                new Movie("GingerBread", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description", 1.776, "test release"),
                new Movie("Honeycomb", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description", 1.776, "test release"),
                new Movie("Ice Cream Sandwich", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description", 1.776, "test release"),
                new Movie("Jelly Bean", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description", 1.776, "test release"),
                new Movie("KitKat", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description", 1.776, "test release"),
                new Movie("Lollipop", "http://image.tmdb.org/t/p/w342//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", "test description", 1.776, "test release")
        };

        mMovieAdapter = new MovieAdapter(this, Arrays.asList(movies));

        // Get a reference to the ListView, and attach this adapter to it.
        GridView movieListView =  findViewById(R.id.movies_gridview);
        movieListView.setAdapter(mMovieAdapter);


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Movie selectedMovie= mMovieAdapter.getItem(position);

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

    }
}
