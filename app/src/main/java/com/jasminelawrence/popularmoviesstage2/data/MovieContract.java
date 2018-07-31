package com.jasminelawrence.popularmoviesstage2.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {

     /* Add content provider constants to the Contract
     Clients need to know how to access the movie data, and it's your job to provide
     these content URI's for the path to that data:
        1) Content authority,
        2) Base content URI,
        3) Path(s) to the movies directory
        4) Content URI for data in the TaskEntry class
      */

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.jasminelawrence.popularmoviesstage2";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "movies" directory
    public static final String PATH_MOVIES = "movies";

    /* MovieEntry is an inner class that defines the contents of the task table */
    public static final class MovieEntry implements BaseColumns {

        // MovieEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();


        // Movie table and column names
        public static final String TABLE_NAME = "movies";

        // Since MovieEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_MOVIE_ID = "movieID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BOX_ART_URL = "boxart";
        public static final String COLUMN_FAVORITE = "favorite";


    }
}
