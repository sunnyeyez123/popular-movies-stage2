/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jasminelawrence.popularmoviesstage2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    private final static String BASE_MOVIE_DB_URL = "http://api.themoviedb.org/3/movie/";
    private final static String REVIEW_MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/";
    private final static String TRAILER_MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/";

    private final static String API_KEY_PARAM = "api_key";
    private final static String API_KEY = BuildConfig.API_KEY;


    private final static String REVIEW_PATH = "reviews";
    private final static String VIDEO_PATH = "videos";




    public static URL buildUrl(String filter) {


        Uri builtUri = Uri.parse(BASE_MOVIE_DB_URL).buildUpon().appendPath(filter).appendQueryParameter(API_KEY_PARAM, API_KEY).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildReviewUrl(String movieID) {


        Uri builtUri = Uri.parse(REVIEW_MOVIE_DB_URL).buildUpon().appendPath(movieID).appendPath(REVIEW_PATH).appendQueryParameter(API_KEY_PARAM, API_KEY).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildTrailerUrl(String moiveID) {


        Uri builtUri = Uri.parse(TRAILER_MOVIE_DB_URL).buildUpon().appendPath(moiveID).appendPath(VIDEO_PATH).appendQueryParameter(API_KEY_PARAM, API_KEY).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}