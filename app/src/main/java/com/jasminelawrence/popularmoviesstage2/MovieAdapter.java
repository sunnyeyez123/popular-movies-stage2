package com.jasminelawrence.popularmoviesstage2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    @BindView(R.id.original_title_tv)
    TextView versionNameView;
    @BindView(R.id.image_iv)
    ImageView iconView;

    public MovieAdapter(Activity context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the movie from the ArrayAdapter at the selected position
        Movie movie = getItem(position);


        //reuse the old views if you have them
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
            ButterKnife.bind(this,convertView);

        }

        //display the movie box art
        Picasso.get().load(movie.getPosterImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iconView);

        //display the movie name
        versionNameView.setText(movie.getOriginalTitle());


        return convertView;
    }
}