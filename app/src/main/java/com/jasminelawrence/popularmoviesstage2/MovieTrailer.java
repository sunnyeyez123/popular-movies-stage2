package com.jasminelawrence.popularmoviesstage2;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieTrailer implements Parcelable {

    private String mName;
    private String mKey;

    public MovieTrailer(String name, String key){
        mName = name;
        mKey = key;

    }

    protected MovieTrailer(Parcel in) {
        mName = in.readString();
        mKey = in.readString();
    }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

    public String getName() {
        return mName;
    }

    public String getKey() {
        return mKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mKey);
    }
}
