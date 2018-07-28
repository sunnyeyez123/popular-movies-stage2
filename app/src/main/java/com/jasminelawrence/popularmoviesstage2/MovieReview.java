package com.jasminelawrence.popularmoviesstage2;

public class MovieReview {

    private String mAuthor;
    private String mContent;

    public MovieReview(String author, String content){
        mAuthor =author;
        mContent = content;

    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmContent() {
        return mContent;
    }
}
