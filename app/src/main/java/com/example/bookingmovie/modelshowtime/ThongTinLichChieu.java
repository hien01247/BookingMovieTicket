package com.example.bookingmovie.modelshowtime;

public class ThongTinLichChieu {
    private int resourceImg;
    private String tenPhim;
    //private String danhGia;
    //private int resourceDinhdangphim;
    //private int resourceDinhdangphim2;
    //private int resourceDinhdangphim3;
    private String doituong;
    //private String namSX;
    private String thoigian;

    public ThongTinLichChieu(int resourceImg, String tenPhim, String doituong, String thoigian) {
        this.resourceImg = resourceImg;
        this.tenPhim = tenPhim;
        this.doituong = doituong;
        this.thoigian = thoigian;
    }
    /* public LichChieu(int resourceImg, String tenPhim, String danhGia, int resourceDinhdangphim, int resourceDinhdangphim2, int resourceDinhdangphim3, String doituong, String namSX, String thoigian) {
        this.resourceImg = resourceImg;
        this.tenPhim = tenPhim;
       this.danhGia = danhGia;
        this.namSX = namSX;
        this.resourceDinhdangphim = resourceDinhdangphim;
        this.resourceDinhdangphim2 = resourceDinhdangphim2;
        this.resourceDinhdangphim3 = resourceDinhdangphim3;
        this.doituong = doituong;
        this.thoigian = thoigian;
    }*/

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

    /*public String getDanhGia() {
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

    public int getResourceDinhdangphim2() {
        return resourceDinhdangphim2;
    }

    public void setResourceDinhdangphim2(int resourceDinhdangphim2) {
        this.resourceDinhdangphim2 = resourceDinhdangphim2;
    }

    public int getResourceDinhdangphim3() {
        return resourceDinhdangphim3;
    }

    public void setResourceDinhdangphim3(int resourceDinhdangphim3) {
        this.resourceDinhdangphim3 = resourceDinhdangphim3;
    }
    public String getNamSX() {
        return namSX;
    }

    public void setNamSX(String namSX) {
        this.namSX = namSX;
    }*/

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
