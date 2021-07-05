package com.example.cinemaapp.model;

public class VeUser {
    private String imgphim;
    private String tenphim;
    private String rap;
    private String ngaychieu;
    private String thoigian;
    private String phong;
    private String vitrighe;
    private String dichvu;
    private String tongtien;
    private String idve;

    public VeUser() {
    }

    public VeUser(String imgphim, String tenphim, String rap, String ngaychieu, String thoigian, String phong, String vitrighe, String dichvu, String tongtien, String idve) {
        this.imgphim = imgphim;
        this.tenphim = tenphim;
        this.rap = rap;
        this.ngaychieu = ngaychieu;
        this.thoigian = thoigian;
        this.phong = phong;
        this.vitrighe = vitrighe;
        this.dichvu = dichvu;
        this.tongtien = tongtien;
        this.idve = idve;
    }


    public String getRap() {
        return rap;
    }

    public void setRap(String rap) {
        this.rap = rap;
    }


    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getVitrighe() {
        return vitrighe;
    }

    public void setVitrighe(String vitrighe) {
        this.vitrighe = vitrighe;
    }

    public String getDichvu() {
        return dichvu;
    }

    public void setDichvu(String dichvu) {
        this.dichvu = dichvu;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getIdve() {
        return idve;
    }

    public void setIdve(String idve) {
        this.idve = idve;
    }

    public String getImgphim() {
        return imgphim;
    }

    public void setImgphim(String imgphim) {
        this.imgphim = imgphim;
    }
}
