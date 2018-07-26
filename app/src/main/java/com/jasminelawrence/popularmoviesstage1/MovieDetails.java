package com.jasminelawrence.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        ButterKnife.bind(this);
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

                nameTextView.setText(name);
                setTitle(name);
            }


            String release_date = extras.getString("RELEASE_DATE");
            if (release_date != null) {
                releaseDateTextView.setText(release_date);
            }

            String plot_synopsis = extras.getString("PLOT");
            if (plot_synopsis != null) {

                plotTextView.setText(plot_synopsis);
            }

            String user_rating = extras.getString("USER_RATING");
            if (user_rating != null) {

                ratingTextView.setText(user_rating);
            }

        }

    }
}
