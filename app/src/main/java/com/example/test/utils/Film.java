package com.example.test.utils;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.List;

public class Film {
    private String id;
    private String localized_name;
    private String name;
    private String year;
    private String rating;
    private Uri image_url;
    private String description;
    private List<String> genres;

    public Film(){}

    public Film(String id, String localized_name, String name, String year,
                String rating, Uri image_url, String description, List<String> genres) {
        this.id = id;
        this.localized_name = localized_name;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.image_url = image_url;
        this.description = description;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public String getLocalized_name() {
        return localized_name;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public Uri getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setImage_url(Uri image_url) {
        this.image_url = image_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
