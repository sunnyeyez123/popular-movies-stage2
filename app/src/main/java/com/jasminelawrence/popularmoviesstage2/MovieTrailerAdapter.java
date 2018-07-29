package com.jasminelawrence.popularmoviesstage2;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

        final MovieTrailer trailer = movieTrailers.get(position);

        if(trailer != null) {


            viewHolder.trailerNameView.setText(trailer.getName());

            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();

                    playTrailer(context, trailer.getKey());

                }
            });

        }

    }



    private static void playTrailer(Context context, String trailerKey) {
        Uri youtubeLink = Uri.parse(NetworkUtils.YOUTUBE_BASE_PATH + trailerKey);
        Intent intent = new Intent(Intent.ACTION_VIEW, youtubeLink);

        // If there is an app to handle the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return  mNumItems;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        private TextView trailerNameView;

        public final View mView;


        public TrailerViewHolder(View view) {
            super(view);
            mView = view;
            trailerNameView = (TextView) view.findViewById(R.id.tailer_name_tv);


        }
    }
}
