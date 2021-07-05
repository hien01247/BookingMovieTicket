package com.example.cinemaapp.model;

public class TaiKhoan {
    private String email;
    private String password;
    private String hoTen;
    private String sdt;
    private int avatar;
    private int diemTichLuy;

    public TaiKhoan(  String email,String password, String hoTen, String sdt, int avatar, int diemTichLuy) {
        this.email = email;
        this.password = password;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.avatar = avatar;
        this.diemTichLuy = diemTichLuy;
    }
    public TaiKhoan(  String email,String password, String hoTen, String sdt) {
        this.email = email;
        this.password = password;
        this.hoTen = hoTen;
        this.sdt = sdt;

    }
    public TaiKhoan() {
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
}
