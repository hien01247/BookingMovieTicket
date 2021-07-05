package com.example.cinemaapp.adapter;

public class ThucUong {
    private int imgThucUong;
    private String tenThucUong;
    private String noidung;
    private int Soluong;
    private int gia;

    public ThucUong(int imgThucUong, String tenThucUong, String noidung, int soluong, int gia) {
        this.imgThucUong = imgThucUong;
        this.tenThucUong = tenThucUong;
        this.noidung = noidung;
        Soluong = soluong;
        this.gia = gia;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }


    public int getImgThucUong() {
        return imgThucUong;
    }

    public void setImgThucUong(int imgThucUong) {
        this.imgThucUong = imgThucUong;
    }

    public String getTenThucUong() {
        return tenThucUong;
    }

    public void setTenThucUong(String tenThucUong) {
        this.tenThucUong = tenThucUong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
