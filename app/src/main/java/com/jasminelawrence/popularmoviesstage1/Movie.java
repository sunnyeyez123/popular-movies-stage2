package com.jasminelawrence.popularmoviesstage1;


import android.os.Parcel;
import android.os.Parcelable;

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
    private String originalTitle;
    private String posterImage;
    private String plotSynopsis;
    private double userRating;
    private String releaseDate;

    public Movie(String originalTitle, String posterImage, String plotSynopsis, double userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.posterImage = posterImage;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }


    // Using the `in` variable, we can retrieve the values that
    // we originally wrote into the `Parcel`.  This constructor is usually
    // private so that only the `CREATOR` field can access.
    private Movie(Parcel in) {

        originalTitle = in.readString();
        posterImage = in.readString();
        plotSynopsis = in.readString();
        userRating = in.readDouble();
        releaseDate = in.readString();

    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public double getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(originalTitle);
        parcel.writeString(posterImage);
        parcel.writeString(plotSynopsis);
        parcel.writeDouble(userRating);
        parcel.writeString(releaseDate);

    }
}
