package com.example.bookingmovie;

public class Promotion {
    private  int image;
    private String content;
    private String detailcontent;

    public Promotion(int image, String content, String detailcontent) {
        this.image = image;
        this.content = content;
        this.detailcontent = detailcontent;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetailcontent() {
        return detailcontent;
    }

    public void setDetailcontent(String detailcontent) {
        this.detailcontent = detailcontent;
    }
}
