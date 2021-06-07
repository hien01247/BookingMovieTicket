package com.example.bookingmovie.model;

import java.util.Date;

public class Ve {
    private String id;
    private Date ngayMua;
    private String idKhachHang;
    private String idLichChieu;
    private String idDichVu;
    private int slDichVu;
    private String viTriGhe;

    public Ve(String id, Date ngayMua, String idKhachHang, String idLichChieu, String idDichVu, int slDichVu, String viTriGhe) {
        this.id = id;
        this.ngayMua = ngayMua;
        this.idKhachHang = idKhachHang;
        this.idLichChieu = idLichChieu;
        this.idDichVu = idDichVu;
        this.slDichVu = slDichVu;
        this.viTriGhe = viTriGhe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getIdLichChieu() {
        return idLichChieu;
    }

    public void setIdLichChieu(String idLichChieu) {
        this.idLichChieu = idLichChieu;
    }

    public String getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(String idDichVu) {
        this.idDichVu = idDichVu;
    }

    public int getSlDichVu() {
        return slDichVu;
    }

    public void setSlDichVu(int slDichVu) {
        this.slDichVu = slDichVu;
    }

    public String getViTriGhe() {
        return viTriGhe;
    }

    public void setViTriGhe(String viTriGhe) {
        this.viTriGhe = viTriGhe;
    }
}
