package com.example.bookingmovie;

public class Ticket {
    private int imgMovie;
    private String nameMovie;
    private String seat;
    private String room;
    private String time;

    public Ticket(int imgMovie, String nameMovie, String seat, String room, String time) {
        this.imgMovie = imgMovie;
        this.nameMovie = nameMovie;
        this.seat = seat;
        this.room = room;
        this.time = time;
    }

    public int getImgMovie() {
        return imgMovie;
    }

    public void setImgMovie(int imgMovie) {
        this.imgMovie = imgMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
