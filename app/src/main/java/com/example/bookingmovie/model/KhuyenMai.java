package com.example.bookingmovie.model;

import java.util.Date;

public class KhuyenMai {
    private String id;
    private String tenKhuyenMai;
    private int img;
    private String noiDung;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public KhuyenMai(String id, String tenKhuyenMai, int img, String noiDung, Date ngayBatDau, Date ngayKetThuc) {
        this.id = id;
        this.tenKhuyenMai = tenKhuyenMai;
        this.img = img;
        this.noiDung = noiDung;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
