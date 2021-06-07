package com.example.bookingmovie.model;

public class PhongChieu {
    private String id;
    private String tenPhong;
    private int soHangGhe;
    private int soGheMoiHang;

    public PhongChieu(String id, String tenPhong, int soHangGhe, int soGheMoiHang) {
        this.id = id;
        this.tenPhong = tenPhong;
        this.soHangGhe = soHangGhe;
        this.soGheMoiHang = soGheMoiHang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getSoHangGhe() {
        return soHangGhe;
    }

    public void setSoHangGhe(int soHangGhe) {
        this.soHangGhe = soHangGhe;
    }

    public int getSoGheMoiHang() {
        return soGheMoiHang;
    }

    public void setSoGheMoiHang(int soGheMoiHang) {
        this.soGheMoiHang = soGheMoiHang;
    }
}
