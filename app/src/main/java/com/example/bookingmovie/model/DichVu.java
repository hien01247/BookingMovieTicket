package com.example.bookingmovie.model;

public class DichVu {
    private String id;
    private String tenDichVu;
    private int gia;

    public DichVu(String id, String tenDichVu, int gia) {
        this.id = id;
        this.tenDichVu = tenDichVu;
        this.gia = gia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
