package com.example.cinemaapp.modelshowtime;

public class ThongTinLichChieu {
    private String resourceImg;
    private String tenPhim;
    private String doituong;

    private String thoigian;

    public ThongTinLichChieu(String resourceImg, String tenPhim, String doituong, String thoigian) {
        this.resourceImg = resourceImg;
        this.tenPhim = tenPhim;
        this.doituong = doituong;
        this.thoigian = thoigian;
    }

    public String getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(String resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }


    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}
