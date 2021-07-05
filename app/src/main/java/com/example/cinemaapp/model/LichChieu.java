package com.example.cinemaapp.model;

public class LichChieu {
    private String id;
    private String idMovie;
    private String rapphim;
    private int phong;
    private String dinhDang;
    private int Gia;
    private String mtrangthaighe;
    private String ngay;
    private String mGio;

    public LichChieu(String id, String idMovie, String rapphim, int phong, String dinhDang, int gia, String mtrangthaighe, String ngay, String mGio) {
        this.id = id;
        this.idMovie = idMovie;
        this.rapphim = rapphim;
        this.phong = phong;
        this.dinhDang = dinhDang;
        Gia = gia;
        this.mtrangthaighe = mtrangthaighe;
        this.ngay = ngay;
        this.mGio = mGio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getRapPhim() {
        return rapphim;
    }

    public void setRapPhim(String tenPhim) {
        this.rapphim = tenPhim;
    }

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }

    public String getDinhDang() {
        return dinhDang;
    }

    public void setDinhDang(String dinhDang) {
        this.dinhDang = dinhDang;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getMtrangthaighe() {
        return mtrangthaighe;
    }

    public void setMtrangthaighe(String mtrangthaighe) {
        this.mtrangthaighe = mtrangthaighe;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getmGio() {
        return mGio;
    }

    public void setmGio(String mGio) {
        this.mGio = mGio;
    }
}
