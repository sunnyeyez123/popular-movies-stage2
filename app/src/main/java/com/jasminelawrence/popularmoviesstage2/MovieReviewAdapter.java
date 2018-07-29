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

public class MovieReviewAdapter extends ArrayAdapter<MovieReview> {


    private static final String LOG_TAG = MovieReviewAdapter.class.getSimpleName();

    @BindView(R.id.review_author_tv)
    TextView authorNameView;
    @BindView(R.id.review_content_tv)
    TextView reviewContentView;

    public MovieReviewAdapter(Activity context, List<MovieReview> reviews) {
        super(context, 0, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the review from the ArrayAdapter at the selected position
        MovieReview review = getItem(position);


        //reuse the old views if you have them
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_list_item, parent, false);
            ButterKnife.bind(this, convertView);

        }

        //display the content name
        authorNameView.setText(review.getAuthor());
        reviewContentView.setText(review.getContent());


        return convertView;
    }
}
