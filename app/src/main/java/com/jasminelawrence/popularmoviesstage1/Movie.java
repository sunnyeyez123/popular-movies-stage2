package com.jasminelawrence.popularmoviesstage1;


public class Movie {

    private String originalTitle;
    private String posterImage;
    private String plotSynopsis;
    private double userRating;
    private String releaseDate;


    Movie(String originalTitle, String posterImage, String plotSynopsis, double userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.posterImage = posterImage;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
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


}
