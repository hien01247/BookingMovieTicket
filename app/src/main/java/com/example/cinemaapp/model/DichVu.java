package com.example.cinemaapp.model;

public class DichVu {
    private String id;
    private String imgdichvu;
    private String tenDichVu;
    private String noidungDV;
    private int soluong;
    private int gia;

    public DichVu(String id, String imgdichvu, String tenDichVu, String noidungDV, int soluong, int gia) {
        this.id = id;
        this.imgdichvu = imgdichvu;
        this.tenDichVu = tenDichVu;
        this.noidungDV = noidungDV;
        this.soluong = soluong;
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

    public String getImgdichvu() {
        return imgdichvu;
    }

    public void setImgdichvu(String imgdichvu) {
        this.imgdichvu = imgdichvu;
    }

    public String getNoidungDV() {
        return noidungDV;
    }

    public void setNoidungDV(String noidungDV) {
        this.noidungDV = noidungDV;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
