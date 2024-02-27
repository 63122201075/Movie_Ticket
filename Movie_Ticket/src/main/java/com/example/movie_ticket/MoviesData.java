package com.example.movie_ticket;

import java.sql.Date;

public class MoviesData {
    private Integer id;
    private String movieTitle;
    private String genre;
    private String duration;
    private String image;
    private Date date; // คอลัมน์ที่เพิ่มเข้ามา
    private String current;

    public MoviesData(Integer id, String movieTitle, String genre, String duration, String image, Date date, String current) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.duration = duration;
        this.image = image;
        this.date = date;
        this.current = current;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

}

