package com.example.cinemaapp.model;

import java.io.Serializable;

public class Phim implements Serializable {
    private String id;
    private String tenPhim;
    private String noiDung;
    private String kiemDuyet;
    private String ngayKhoiChieu;
    private String theLoai;
    private String thoiLuong;
    private String image;
    private String tinhTrang;
    private String idtrailer;

    public Phim(String id, String tenPhim, String noiDung, String kiemDuyet, String ngayKhoiChieu, String theLoai, String thoiLuong, String image, String tinhTrang, String idtrailer) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.noiDung = noiDung;
        this.kiemDuyet = kiemDuyet;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.image = image;
        this.tinhTrang = tinhTrang;
        this.idtrailer = idtrailer;
    }

    public Phim() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getKiemDuyet() {
        return kiemDuyet;
    }

    public void setKiemDuyet(String kiemDuyet) {
        this.kiemDuyet = kiemDuyet;
    }

    public String getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(String ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }


    public String getIdtrailer() {
        return idtrailer;
    }

    public void setIdtrailer(String idtrailer) {
        this.idtrailer = idtrailer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
