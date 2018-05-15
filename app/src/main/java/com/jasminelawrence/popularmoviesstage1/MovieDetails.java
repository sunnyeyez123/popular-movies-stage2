package com.jasminelawrence.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(MovieDetails.this, "No movie data", Toast.LENGTH_SHORT).show();

        }else{
            // get data via the key
            String name = extras.getString("ORIGINAL_NAME");
            if (name != null) {
                // do something with the data

                TextView nameTextView = findViewById(R.id.original_name_tv);
                nameTextView.setText(name);
                setTitle(name);
            }

        }

    }
}