package com.jasminelawrence.popularmoviesstage2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetails extends AppCompatActivity {

    @BindView(R.id.original_title_tv)
    TextView nameTextView;
    @BindView(R.id.release_date_tv)
    TextView releaseDateTextView;
    @BindView(R.id.plot_synopsis_tv)
    TextView plotTextView;
    @BindView(R.id.user_rating_tv)
    TextView ratingTextView;
    @BindView(R.id.poster_image_iv)
    ImageView iconView;


    private ImageButton favoriteButton;
    private RecyclerView movieReviewListView;


    private RecyclerView movieTrailerListView;

    private MovieReviewAdapter mMovieReviewAdapter;

    private MovieTrailerAdapter mMovieTrailerAdapter;


    private ArrayList<MovieReview> mMovieReviewList;
    private ArrayList<MovieTrailer> mMovieTrailerList;
    private String movieID;
    private Movie movie;

    private static ArrayList<MovieReview> extractReviewFeatureFromJson(String MovieReviewsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(MovieReviewsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding movies
        ArrayList<MovieReview> reviewList = new ArrayList<>();


        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(MovieReviewsJSON);

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of items (or reviews).

            JSONArray reviewsArray = baseJsonResponse.getJSONArray("results");

            // For each Movie in the MovieReviewArray, create an Movie reivew object
            for (int i = 0; i < reviewsArray.length(); i++) {

                // Get a single movie at position i within the list of reviews
                JSONObject currentReview = reviewsArray.getJSONObject(i);

                //get the relevant information about the review
                String author = currentReview.getString("author");

                String content = currentReview.getString("content");


                // Create a new Review object with the information
                MovieReview movieReview = new MovieReview(author, content);

                // Add the new movie to the list of movies
                reviewList.add(movieReview);

            }

        } catch (JSONException e) {

            Log.e("Movie Details", "Problem parsing the Movie Review JSON results", e);

        }

        // Return the list of movies
        return reviewList;
    }

    private static ArrayList<MovieTrailer> extractTrailerFeatureFromJson(String MovieTrailerJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(MovieTrailerJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding movies
        ArrayList<MovieTrailer> trailerList = new ArrayList<>();


        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(MovieTrailerJSON);

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of items (or trailers).

            JSONArray reviewsArray = baseJsonResponse.getJSONArray("results");

            // For each Movie in the TrailerArray, create an Trailer object
            for (int i = 0; i < reviewsArray.length(); i++) {

                // Get a single trailer at position i within the list of Movies
                JSONObject currentReview = reviewsArray.getJSONObject(i);

                //get the relevant information about the trailer
                String name = currentReview.getString("name");

                String video_key = currentReview.getString("key");


                // Create a new trailer object with the information
                MovieTrailer movieTrailer = new MovieTrailer(name, video_key);

                // Add the new trailer to the list of movies
                trailerList.add(movieTrailer);


            }

        } catch (JSONException e) {

            Log.e("Movie Details", "Problem parsing the Movie Trailer JSON results", e);

        }

        // Return the list of movies
        return trailerList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        ButterKnife.bind(this);
        Bundle data = getIntent().getExtras();

        movieReviewListView = findViewById(R.id.review_list);
        movieTrailerListView = findViewById(R.id.trailer_list);

        //create and set layout manager for each RecyclerView
        RecyclerView.LayoutManager reviewLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager trailerLayoutManager = new LinearLayoutManager(this);

        movieReviewListView.setLayoutManager(reviewLayoutManager);
        movieTrailerListView.setLayoutManager(trailerLayoutManager);


        movie = data.getParcelable("theMovieDetails");

        if (movie == null) {
            Toast.makeText(MovieDetails.this, "No movie com.jasminelawrence.popularmoviesstage2.data", Toast.LENGTH_SHORT).show();

        } else {

            movieID = String.valueOf(movie.getID());


            String poster_image = movie.getPosterImage();

            Picasso.get().load(poster_image)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(iconView);


            String name = movie.getOriginalTitle();

            if (name != null) {
                nameTextView.setText(name);
                setTitle(name);

            }

            String release_date = movie.getReleaseDate();
            if (release_date != null) {
                releaseDateTextView.setText(release_date);
            }

            String plot_synopsis = movie.getPlotSynopsis();
            if (plot_synopsis != null) {

                plotTextView.setText(plot_synopsis);
            }

            String user_rating = String.valueOf(movie.getUserRating());
            if (user_rating != null) {

                ratingTextView.setText(user_rating);
            }


            getMovieReviews(movieID);
            getMovieTrailers(movieID);


        }



        createFavoriteButtonClickListener();


    }


    private void createFavoriteButtonClickListener() {


         favoriteButton = (ImageButton) findViewById(R.id.favorite_btn);

        favoriteButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(MovieDetails.this,
                        "ImageButton is clicked!", Toast.LENGTH_SHORT).show();

                if(movie.isFavorite()){       //unmark as favorite (change icon and remove from db)

              //      favoriteButton.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
                    favoriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
                    movie.setIsFavorite(false);


                }else{                  //mark as favorite (change icon and add to db)
             //       favoriteButton.setBackgroundResource(R.drawable.ic_star_black_24dp);
                    favoriteButton.setImageResource(R.drawable.ic_star_black_24dp);

                    movie.setIsFavorite(true);


                }


            }

        });
    }

        private void getMovieReviews(String movieID) {


        URL reviewURL = NetworkUtils.buildReviewUrl(movieID);

        new MovieReviewTask().execute(reviewURL);


    }

    private void getMovieTrailers(String moiveID) {


        URL trailerURL = NetworkUtils.buildTrailerUrl(moiveID);
        new MovieTrailerTask().execute(trailerURL);


    }

    private class MovieReviewTask extends AsyncTask<URL, Void, Void> {

        @Override
        protected Void doInBackground(URL... params) {
            URL searchUrl = params[0];
            String movieReviewResults;

            try {

                movieReviewResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                mMovieReviewList = extractReviewFeatureFromJson(movieReviewResults);


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {


            if (mMovieReviewList != null && mMovieReviewList.size() > 0) {

                mMovieReviewAdapter = new MovieReviewAdapter(mMovieReviewList, mMovieReviewList.size(), MovieDetails.this);
                movieReviewListView.setAdapter(mMovieReviewAdapter);
            }

        }

    }

    private class MovieTrailerTask extends AsyncTask<URL, Void, Void> {

        @Override
        protected Void doInBackground(URL... params) {
            URL searchUrl = params[0];
            String movieTrailerResults;
            try {
                movieTrailerResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                mMovieTrailerList = extractTrailerFeatureFromJson(movieTrailerResults);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

            if (mMovieTrailerList != null && mMovieTrailerList.size() > 0) {

                mMovieTrailerAdapter = new MovieTrailerAdapter(mMovieTrailerList, mMovieTrailerList.size(), MovieDetails.this);
                movieTrailerListView.setAdapter(mMovieTrailerAdapter);
            }

        }

    }


}
