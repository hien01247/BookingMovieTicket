package com.example.bookingmovie.model;

public class LichChieu {
    private String id;
    private String idPhim;
    private String idPhong;
    private String dinhDang;
    private String thoiGian;
    private String ngayChieu;
    private int gia;

    public LichChieu(String id, String idPhim, String idPhong, String dinhDang, String thoiGian, String ngayChieu, int gia) {
        this.id = id;
        this.idPhim = idPhim;
        this.idPhong = idPhong;
        this.dinhDang = dinhDang;
        this.thoiGian = thoiGian;
        this.ngayChieu = ngayChieu;
        this.gia = gia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getDinhDang() {
        return dinhDang;
    }

    public void setDinhDang(String dinhDang) {
        this.dinhDang = dinhDang;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
