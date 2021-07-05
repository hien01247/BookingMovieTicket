package com.example.cinemaapp.modelshowtime;

public class NgayChieu {
    private String tenThu;
    private String ngay;

    public NgayChieu(String tenThu, String ngay) {
        this.tenThu = tenThu;
        this.ngay = ngay;
    }

    public String getTenThu() {
        return tenThu;
    }

    public void setTenThu(String tenThu) {
        this.tenThu = tenThu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
