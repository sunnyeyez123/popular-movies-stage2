package com.jasminelawrence.popularmoviesstage2;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieReview implements Parcelable {

    private String mAuthor;
    private String mContent;

    public MovieReview(String author, String content){
        mAuthor =author;
        mContent = content;

    }

    private MovieReview(Parcel in) {
        mAuthor = in.readString();
        mContent = in.readString();
    }

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeString(mAuthor);
        out.writeString(mContent);

    }
}
