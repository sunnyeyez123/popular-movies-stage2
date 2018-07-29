package com.jasminelawrence.popularmoviesstage2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieTrailerAdapter extends ArrayAdapter<MovieTrailer> {


    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    @BindView(R.id.review_author_tv)
    TextView trailerNameView;


    public MovieTrailerAdapter(Activity context, List<MovieTrailer> trailers) {
        super(context, 0, trailers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the review from the ArrayAdapter at the selected position
        MovieTrailer trailer = getItem(position);


        //reuse the old views if you have them
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_list_item, parent, false);
            ButterKnife.bind(this, convertView);

        }

        //display the content name
        trailerNameView.setText(trailer.getName());
        return convertView;
    }
}
