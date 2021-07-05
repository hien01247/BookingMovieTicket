package com.example.cinemaapp.model;public class ThanhVien {
    private String avatar;
    private String ten;
    private String namsinh;
    private String chuyennganh;

    public ThanhVien(String avatar, String ten, String namsinh, String chuyennganh) {
        this.avatar = avatar;
        this.ten = ten;
        this.namsinh = namsinh;
        this.chuyennganh = chuyennganh;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }
}
