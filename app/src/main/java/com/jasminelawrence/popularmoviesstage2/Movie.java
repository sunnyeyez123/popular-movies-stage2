package com.jasminelawrence.popularmoviesstage2;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie implements Parcelable {


    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private String mOriginalTitle;
    private String mPosterImage;
    private String mPlotSynopsis;
    private double mUserRating;
    private String mReleaseDate;
    private double mID;
    private ArrayList<MovieReview> mReviews;
    private ArrayList<MovieTrailer> mTrailers;
    private boolean mIsFavorite = false;


    public void setReviews(ArrayList<MovieReview> mReviews) {
        this.mReviews = mReviews;
    }

    public void setTrailers(ArrayList<MovieTrailer> mTrailers) {
        this.mTrailers = mTrailers;
    }

    public void setIsFavorite(boolean mIsFavorite) {
        this.mIsFavorite = mIsFavorite;
    }

    public Movie(double id, String originalTitle, String posterImage, String plotSynopsis, double userRating, String releaseDate, ArrayList<MovieReview> reviews, ArrayList<MovieTrailer> trailers) {
        mOriginalTitle = originalTitle;
        mPosterImage = posterImage;
        mPlotSynopsis = plotSynopsis;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
        mID = id;
        mReviews = reviews;
        mTrailers = trailers;


    }

    // Using the `in` variable, we can retrieve the values that
    // we originally wrote into the `Parcel`.  This constructor is usually
    // private so that only the `CREATOR` field can access.
    private Movie(Parcel in) {

        mOriginalTitle = in.readString();
        mPosterImage = in.readString();
        mPlotSynopsis = in.readString();
        mUserRating = in.readDouble();
        mReleaseDate = in.readString();
        mID = in.readDouble();
        mReviews = in.createTypedArrayList(MovieReview.CREATOR);
        mTrailers = in.createTypedArrayList(MovieTrailer.CREATOR);
        mIsFavorite = in.readByte() != 0;


    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getPosterImage() {
        return mPosterImage;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public double getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public double getID() {
        return mID;
    }

    public ArrayList<MovieReview> getReviews() {
        return mReviews;
    }

    public ArrayList<MovieTrailer> getTrailers() {
        return mTrailers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mOriginalTitle);
        parcel.writeString(mPosterImage);
        parcel.writeString(mPlotSynopsis);
        parcel.writeDouble(mUserRating);
        parcel.writeString(mReleaseDate);
        parcel.writeDouble(mUserRating);
        parcel.writeDouble(mID);
        parcel.writeTypedList(mReviews);
        parcel.writeTypedList(mTrailers);
        parcel.writeByte((byte) (mIsFavorite ? 1 : 0));


    }


}
