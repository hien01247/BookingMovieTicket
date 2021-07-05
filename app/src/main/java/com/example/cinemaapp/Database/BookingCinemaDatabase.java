package com.example.cinemaapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cinemaapp.model.LichChieu;
import com.example.cinemaapp.modelshowtime.SuatChieu;

import java.util.ArrayList;
import java.util.List;

public class BookingCinemaDatabase {
    private SQLiteDatabase database ;
    private BookingCinemaHelper dbHelper;
    public  BookingCinemaDatabase(Context context){
        dbHelper = new BookingCinemaHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public boolean insertTaiKhoan(String username,String password,String hoten, String email,String sdt,int avatar,int diemtichluy){
        ContentValues values= new ContentValues();
        values.put("username",username);
        values.put("password",password);
        values.put("hoten",hoten);
        values.put("email",email);
        values.put("sdt",sdt);
        values.put("avatar",avatar);
        values.put("diemtichluy",diemtichluy);
        long insert=database.insert("TaiKhoan", null,values);
        if (insert == -1) return false;
        return true;
    }
    //lấy thông tin tài khoản user
//    public TaiKhoan getTaiKhoanByUsername(String username) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query("TaiKhoan", null, "username" + " = ?", new String[] {username},null, null, null);
//        if(cursor != null)
//            cursor.moveToFirst();
//        TaiKhoan taiKhoan = new TaiKhoan(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7));
//        return taiKhoan;
//    }
//    public TaiKhoan getTaiKhoanByEmail(String email) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query("TaiKhoan", null, "email" + " = ?", new String[] {email},null, null, null);
//        if(cursor != null)
//            cursor.moveToFirst();
//        TaiKhoan taiKhoan = new TaiKhoan(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7));
//        return taiKhoan;
//    }


    //Kiểm tra username
    public  Boolean checkUsername(String username){
        Cursor cursor = database.rawQuery("Select * from TaiKhoan where username = ?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //Kiểm tra email
    public  Boolean checkEmail(String email){
        Cursor cursor = database.rawQuery("Select * from TaiKhoan where email = ?",new String[] {email});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //Kiểm tra đăng nhập
    public Boolean checkPassword(String username, String password){
        Cursor cursor = database.rawQuery("Select * from TaiKhoan where username = ? and password = ?",new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //
    public Boolean insertDichVu(String tendichvu, int gia){
        ContentValues values = new ContentValues();
        values.put("tendichvu",tendichvu);
        values.put("gia",gia);
        long insert=database.insert("DichVu",null,values);
        if (insert == -1) return false;
        return true;
    }
    //
    public Boolean insertKhuyenMai(String tenkhuyenmai,String noidung,int imgkhuyenmai){
        ContentValues values = new ContentValues();
        values.put("tenkhuyenmai",tenkhuyenmai);
        values.put("noidung",noidung);
        values.put("imgkhuyenmai",imgkhuyenmai);
        long insert=database.insert("KhuyenMai",null,values);
        if (insert == -1) return false;
        return true;
    }
    public Boolean insertPhongChieu(String tenphongchieu,int sohangghe,int soghemoihang){
        ContentValues values = new ContentValues();
        values.put("tenphongchieu",tenphongchieu);
        values.put("sohangghe",sohangghe);
        values.put("soghemoihang",soghemoihang);
        long insert = database.insert("PhongChieu",null,values);
        if (insert == -1) return false;
        return true;
    }
    public Boolean insertPhim(String tenphim,String noidung,String kiemduyet,String ngaykhoichieu,String theloai,String thoiluong,int imgphim,int tinhtrang,String idtrailer){
        ContentValues values = new ContentValues();
        values.put("tenphim",tenphim);
        values.put("noidung",noidung);
        values.put("kiemduyet",kiemduyet);
        values.put("ngaykhoichieu",ngaykhoichieu);
        values.put("theloai",theloai);
        values.put("thoiluong",thoiluong);
        values.put("imgphim",imgphim);
        values.put("tinhtrang",tinhtrang);
        values.put("idtrailer",idtrailer);
        long insert = database.insert("Phim",null,values);
        if (insert == -1) return false;
        return true;
    }
//    public List<ThongTinLichChieu> getThongTinLichChieu( ) {
//        List<ThongTinLichChieu>  List = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT p.tenphim,p.kiemduyet,p.thoiluong,p.imgphim FROM Phim p",null);
//        cursor.moveToFirst();
//        while(cursor.isAfterLast() == false) {
//            ThongTinLichChieu thongTinLichChieu= new ThongTinLichChieu(cursor.getInt(3),cursor.getString(0),cursor.getString(1),cursor.getString(2));
//            List.add(thongTinLichChieu);
//            cursor.moveToNext();
//        }
//        return List;
//    }
    public List<LichChieu> getShowTime(String tenPhim, String gioChieu) {
        List<LichChieu> list= new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT lc.id,p.id,p.tenphim,lc.phong,lc.dinhdang,lc.gia,lc.trangthai,lc.ngaychieu,lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim = ? and lc.thoigian= ?", new String[]{tenPhim, gioChieu});
        if(cursor != null)
            cursor.moveToFirst();
        LichChieu lichChieu = new LichChieu(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        list.add(lichChieu);
        return list;
    }

    public Boolean insertLichChieu(int idphim,int phong,String dinhdang,String thoigian,String ngaychieu,int gia,String trangthai){
        ContentValues values = new ContentValues();
        values.put("idphim",idphim);
        values.put("phong",phong);
        values.put("dinhdang",dinhdang);
        values.put("thoigian",thoigian);
        values.put("ngaychieu",ngaychieu);
        values.put("gia",gia);
        values.put("trangthai",trangthai);
        long insert = database.insert("LichChieu",null,values);
        if (insert == -1) return false;
        return true;
    }
    //lay thông tin lich chiếu
    private LichChieu getLichChieu(String lichChieuId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("LichChieu", null, "id" + " = ?", new String[] { lichChieuId},null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        LichChieu lichChieu = new LichChieu(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        return lichChieu;
    }
    public String getTrangThaiGhe(String tenPhim, String gioChieu) {
        String mlistTrangThai = "";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT lc.trangthai FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim = ? and lc.thoigian= ?", new String[]{tenPhim, gioChieu});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            mlistTrangThai = cursor.getString(0);
            cursor.moveToNext();
        }
        return mlistTrangThai;
    }
    //lấy xuất chiếu theo tên phim
    public List<SuatChieu> getXuatChieu(String tenP) {
        List<SuatChieu>  xcList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim = ?",new String[] {tenP});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            SuatChieu suatChieu = new SuatChieu(cursor.getString(0));
            xcList.add(suatChieu);
            cursor.moveToNext();
        }
        return xcList;
    }
//    public List<KhuyenMai> getKhuyenMai(){
//        List<KhuyenMai> khuyenMais = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from KhuyenMai",null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            KhuyenMai khuyenMai = new KhuyenMai(cursor.getString(0),cursor.getString(1),cursor.getInt(3),cursor.getString(2));
//            khuyenMais.add(khuyenMai);
//            cursor.moveToNext();
//        }
//        return khuyenMais;
//    }
//    public List<Phim> getPhim(){
//        List<Phim> phims = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from Phim where tinhtrang = 1",null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            Phim phim = new Phim(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9));
//            phims.add(phim);
//            cursor.moveToNext();
//        }
//        return phims;
//    }phims
//    public List<Phim> getPhim0(){
//        List<Phim> phims = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from Phim where tinhtrang = 0",null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            Phim phim = new Phim(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9));
//            phims.add(phim);
//            cursor.moveToNext();
//        }
//        return phims;
//    }
//    public Phim getPhimbyTen(String tenphim){
//        SQLiteDatabase db =dbHelper.getReadableDatabase();
//        Cursor cursor = db.query("Phim", null, "tenphim" + " = ?", new String[] {tenphim},null, null, null);
//        if(cursor != null)
//            cursor.moveToFirst();
//        Phim phim = new Phim(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9));
//        return phim;
//    }
    public Boolean insertVe(String idkhachhang,String idlichchieu,String vitrighe,String dichvu,String mave,String tongtien){
        ContentValues values = new ContentValues();
        values.put("idkhachhang",idkhachhang);
        values.put("idlichchieu",idlichchieu);
        values.put("vitrighe",vitrighe);
        values.put("dichvu",dichvu);
        values.put("mave",mave);
        values.put("tongtien",tongtien);
        long insert = database.insert("Ve",null,values);
        if (insert == -1) return false;
        return true;
    }

//    public void updatebyEmail(TaiKhoan taiKhoan){
//        ContentValues values= new ContentValues();
//        values.put("username",taiKhoan.getUsername());
//        values.put("password",taiKhoan.getPassword());
//        values.put("hoten",taiKhoan.getHoTen());
//        values.put("sdt",taiKhoan.getSdt());
//        values.put("email",taiKhoan.getEmail());
//        values.put("avatar",taiKhoan.getAvatar());
//        values.put("diemtichluy",taiKhoan.getDiemTichLuy());
//        database.update("TaiKhoan", values,"email = ?", new String[] {taiKhoan.getEmail()});
//    }
//
//    public void updatebyUsername(TaiKhoan taiKhoan){
//        ContentValues values= new ContentValues();
//        values.put("username",taiKhoan.getUsername());
//        values.put("password",taiKhoan.getPassword());
//        values.put("hoten",taiKhoan.getHoTen());
//        values.put("sdt",taiKhoan.getSdt());
//        values.put("email",taiKhoan.getEmail());
//        values.put("avatar",taiKhoan.getAvatar());
//        values.put("diemtichluy",taiKhoan.getDiemTichLuy());
//        database.update("TaiKhoan", values ,"username = ?", new String[] {taiKhoan.getUsername()});
//    }

    public Boolean insertVe(int idkhachhang,int idlichchieu, String vitrighe, String dichVu, String code,String tongtien){
        ContentValues values = new ContentValues();
        values.put("idkhachhang",idkhachhang);
        values.put("idlichchieu",idlichchieu);
        values.put("vitrighe",vitrighe);
        values.put("dichvu",dichVu);
        values.put("mave",code);
        values.put("tongtien",tongtien);
        long insert = database.insert("Ve",null,values);
        if (insert == -1) return false;
        return true;
    }
    public  void updateTrangThaiGhe(String idlichChieu, String listTrangThai){
        //UPDATE Students SET DepartmentId = 3 WHERE StudentId = 6;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE LichChieu SET trangthai=?  WHERE id=?",new String[]{listTrangThai, idlichChieu});
    }
//    public List<VeUser> getVeUser(String idUser){
//        List<VeUser> veuser = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select p.imgphim,p.tenphim,lc.ngaychieu,lc.thoigian,lc.phong,v.vitrighe,v.dichvu,v.tongtien,v.mave FROM LichChieu lc,Phim p, Ve v Where v.idlichchieu=lc.id AND lc.idphim= p.id AND v.idkhachhang = ? ",new String[]{idUser});
//        cursor.moveToFirst();
//        while(cursor.isAfterLast() == false) {
//            VeUser ve = new VeUser(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
//            veuser.add(ve);
//            cursor.moveToNext();
//        }
//        return veuser;
//    }

//    public VeUser getVebyID(String id){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select p.imgphim,p.tenphim,lc.ngaychieu,lc.thoigian,lc.phong,v.vitrighe,v.dichvu,v.tongtien,v.mave FROM LichChieu lc,Phim p, Ve v Where v.idlichchieu=lc.id AND lc.idphim= p.id AND v.mave=?",new String[]{id});
//        cursor.moveToFirst();
//        VeUser ve = new VeUser(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
//        return ve;
//    }
//    public Ve getVe(String ma) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * FROM Ve where mave =?",new String[]{ma});
//        if(cursor != null)
//            cursor.moveToFirst();
//        Ve ve = new Ve(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
//        return ve;
//    }
    public int tongDoanhThu(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select v.tongtien From Ve v, TaiKhoan tk Where v.idkhachhang = tk.id AND tk.username = ?",new String[]{username});
        cursor.moveToFirst();
        int Doanhthu = 0;
        while(cursor.isAfterLast() == false) {
            Doanhthu = cursor.getInt(0);
            cursor.moveToNext();
        }
        return Doanhthu;
    }

}
