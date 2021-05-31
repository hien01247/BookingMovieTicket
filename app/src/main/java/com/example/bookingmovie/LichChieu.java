package com.example.bookingmovie;

public class LichChieu {
    private int resourceImg;
    private String tenPhim;
    private String danhGia;
    private int resourceDinhdangphim;
    private String xc1;
    private String xc2;
    private String xc3;

    public LichChieu(int resourceImg, String tenPhim, String danhGia, int resourceDinhdangphim, String xc1, String xc2, String xc3) {
        this.resourceImg = resourceImg;
        this.tenPhim = tenPhim;
        this.danhGia = danhGia;
        this.resourceDinhdangphim = resourceDinhdangphim;
        this.xc1 = xc1;
        this.xc2 = xc2;
        this.xc3 = xc3;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public int getResourceDinhdangphim() {
        return resourceDinhdangphim;
    }

    public void setResourceDinhdangphim(int resourceDinhdangphim) {
        this.resourceDinhdangphim = resourceDinhdangphim;
    }

    public String getXc1() {
        return xc1;
    }

    public void setXc1(String xc1) {
        this.xc1 = xc1;
    }

    public String getXc2() {
        return xc2;
    }

    public void setXc2(String xc2) {
        this.xc2 = xc2;
    }

    public String getXc3() {
        return xc3;
    }

    public void setXc3(String xc3) {
        this.xc3 = xc3;
    }
}
