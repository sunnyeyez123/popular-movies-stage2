package com.jasminelawrence.popularmoviesstage2;


        import android.app.Activity;
        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import java.util.ArrayList;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ReviewViewHolder> {

    private ArrayList<MovieReview> movieReviews;
    private Context mContext;
    private int mNumItems;

    public MovieReviewAdapter(ArrayList<MovieReview> reviews, int numItems, Context context) {
        mContext = context;
        mNumItems = numItems;
        movieReviews = reviews;
    }

    @Override
    public MovieReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.review_list_item, parent, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewAdapter.ReviewViewHolder viewHolder, int position) {

        MovieReview review = movieReviews.get(position);

        if(review != null) {

            viewHolder.authorNameView.setText(review.getAuthor());
            viewHolder.reviewContentView.setText(review.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return  mNumItems;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView authorNameView;
        private TextView reviewContentView;


        public ReviewViewHolder(View view) {
            super(view);
            authorNameView = (TextView) view.findViewById(R.id.review_author_tv);
            reviewContentView = (TextView) view.findViewById(R.id.review_content_tv);

        }
    }
}
