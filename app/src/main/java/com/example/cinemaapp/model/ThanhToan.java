package com.example.cinemaapp.model;

public class ThanhToan {
    private int imgThanhToan;
    private String tenThanhToan;

    public ThanhToan(int imgThanhToan, String tenThanhToan) {
        this.imgThanhToan = imgThanhToan;
        this.tenThanhToan = tenThanhToan;
    }

    public int getImgThanhToan() {
        return imgThanhToan;
    }

    public void setImgThanhToan(int imgThanhToan) {
        this.imgThanhToan = imgThanhToan;
    }

    public String getTenThanhToan() {
        return tenThanhToan;
    }

    public void setTenThanhToan(String tenThanhToan) {
        this.tenThanhToan = tenThanhToan;
    }
}
