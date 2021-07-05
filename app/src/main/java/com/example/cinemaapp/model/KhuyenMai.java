package com.example.cinemaapp.model;

public class KhuyenMai {
    private String id;
    private String tenKhuyenMai;
    private String img;
    private String noiDung;

    public KhuyenMai(String id, String tenKhuyenMai, String img, String noiDung) {
        this.id = id;
        this.tenKhuyenMai = tenKhuyenMai;
        this.img = img;
        this.noiDung = noiDung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

}
