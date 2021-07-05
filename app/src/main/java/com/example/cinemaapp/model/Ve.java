package com.example.cinemaapp.model;

public class Ve {
    private String id;
    private String idKhachhang;
    private String idLichChieu;
    private String viTriGhe;
    private String dichVu;
    private String maVe;
    private String tongTien;

    public Ve(String id, String idKhachhang, String idLichChieu, String viTriGhe, String dichVu, String maVe, String tongTien) {
        this.id = id;
        this.idKhachhang = idKhachhang;
        this.idLichChieu = idLichChieu;
        this.viTriGhe = viTriGhe;
        this.dichVu = dichVu;
        this.maVe = maVe;
        this.tongTien = tongTien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKhachhang() {
        return idKhachhang;
    }

    public void setIdKhachhang(String idKhachhang) {
        this.idKhachhang = idKhachhang;
    }

    public String getIdLichChieu() {
        return idLichChieu;
    }

    public void setIdLichChieu(String idLichChieu) {
        this.idLichChieu = idLichChieu;
    }

    public String getViTriGhe() {
        return viTriGhe;
    }

    public void setViTriGhe(String viTriGhe) {
        this.viTriGhe = viTriGhe;
    }

    public String getDichVu() {
        return dichVu;
    }

    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
