package com.jasminelawrence.popularmoviesstage2;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.TrailerViewHolder> {

    private ArrayList<MovieTrailer> movieTrailers;
    private Context mContext;
    private int mNumItems;

    public MovieTrailerAdapter(ArrayList<MovieTrailer> trailers, int numItems, Context context) {
        mContext = context;
        mNumItems = numItems;
        movieTrailers = trailers;
    }

    @Override
    public MovieTrailerAdapter.TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.trailer_list_item, parent, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerAdapter.TrailerViewHolder viewHolder, int position) {

        MovieTrailer trailer = movieTrailers.get(position);

        if(trailer != null) {


            viewHolder.trailerNameView.setText(trailer.getName());

        }

    }

    @Override
    public int getItemCount() {
        return  mNumItems;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        private TextView trailerNameView;


        public TrailerViewHolder(View view) {
            super(view);
            trailerNameView = (TextView) view.findViewById(R.id.tailer_name_tv);


        }
    }
}
