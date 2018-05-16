package com.jasminelawrence.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(MovieDetails.this, "No movie data", Toast.LENGTH_SHORT).show();

        } else {


            String poster_image = extras.getString("POSTER_IMAGE");
            if (poster_image != null) {

                ImageView iconView = findViewById(R.id.poster_image_iv);
                Picasso.get().load(poster_image)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(iconView);
            }





            // get data via the key
            String name = extras.getString("ORIGINAL_NAME");
            if (name != null) {

                TextView nameTextView = findViewById(R.id.original_title_tv);
                nameTextView.setText(name);
                setTitle(name);
            }


            String release_date = extras.getString("RELEASE_DATE");
            if (release_date != null) {

                TextView releaseDateTextView = findViewById(R.id.release_date_tv);
                releaseDateTextView.setText(release_date);
            }

            String plot_synopsis = extras.getString("PLOT");
            if (plot_synopsis != null) {

                TextView plotTextView = findViewById(R.id.plot_synopsis_tv);
                plotTextView.setText(plot_synopsis);
            }

            String user_rating = extras.getString("USER_RATING");
            if (user_rating != null) {

                TextView ratingTextView = findViewById(R.id.user_rating_tv);
                ratingTextView.setText(user_rating);
            }

        }

    }
}
