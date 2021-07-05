package com.example.cinemaapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookingCinemaHelper extends SQLiteOpenHelper{
    //Tui làm như bài sql hôi bữa của cha
    private static final String DATABASE_NAME = "bookingcinema.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TaiKhoan = "create table TaiKhoan (id integer primary key autoincrement, "
                                            + "username text not null,password text not null, hoten text not null, " +
                                                "email text not null, sdt text not null, avatar integer, diemtichluy integer not null)";
    private static final String DichVu = "create table DichVu (id integer primary key autoincrement, "
                                            + "tendichvu text not null, gia integer not null)";
    private static final String KhuyenMai = "create table KhuyenMai (id integer primary key autoincrement, "
                                            + "tenkhuyenmai text not null, noidung text not null, "
                                            + "imgkhuyenmai integer not null)";
    private static final String LichChieu = "create table LichChieu (id integer primary key autoincrement, idphim integer not null, phong integer not null, "
            + "dinhdang text not null, thoigian text not null, ngaychieu text not null, gia integer not null,trangthai text not null,constraint fk1 foreign key (idphim) references Phim(id))";
    private static final String Phim = "create table Phim (id integer primary key autoincrement, tenphim text not null, noidung text not null, "
                                        + "kiemduyet text not null,ngaykhoichieu text not null, theloai text not null, thoiluong text not null,imgphim integer not null,tinhtrang interger not null,idtrailer text not null)";
    private static final  String Ve = "create table Ve (id integer primary key autoincrement, idkhachhang integer not null,idlichchieu integer not null, "
            + "vitrighe text not null,dichvu text not null,mave text not null,tongtien text not null, constraint fk3 foreign key (idkhachhang) references Khachhang(id), constraint fk4 foreign key (idlichchieu) references LichChieu(id))";

    public BookingCinemaHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DichVu);
        db.execSQL(TaiKhoan);
        db.execSQL(KhuyenMai);
        db.execSQL(Phim);
        db.execSQL(LichChieu);
        db.execSQL(Ve);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DichVu");
        db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
        db.execSQL("DROP TABLE IF EXISTS KhuyenMai");
        db.execSQL("DROP TABLE IF EXISTS Phim");
        db.execSQL("DROP TABLE IF EXISTS LichChieu");
        db.execSQL("DROP TABLE IF EXISTS Ve");
        onCreate(db);
    }
}

