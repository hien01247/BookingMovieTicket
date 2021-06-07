package com.example.bookingmovie.model;
import java.util.Date;

public class Phim {
    private String id;
    private String tenPhim;
    private String noiDung;
    private String kiemDuyet;
    private Date ngayKhoiChieu;
    private String theLoai;
    private int thoiLuong;
    private int image;

    public Phim(String id, String tenPhim, String noiDung, String kiemDuyet, Date ngayKhoiChieu, String theLoai, int thoiLuong, int image) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.noiDung = noiDung;
        this.kiemDuyet = kiemDuyet;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.image = image;
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

    public Date getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(Date ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
