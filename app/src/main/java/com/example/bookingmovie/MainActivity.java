package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.DatabaseUser;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private  Toolbar toolbar;
    private long pressedTime;
    private BookingCinemaDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
        getSupportActionBar().setTitle("Trang chủ");
        View headerView = navigationView.getHeaderView(0);
        TextView navName = headerView.findViewById(R.id.nav_name);
        TextView navEmail = headerView.findViewById(R.id.nav_mail);
        String s1 = "Username";
        String s2 = "Name";
        navEmail.setText(s1);
        navName.setText(s2);
        updateNav();
        if (navEmail.getText().toString()!="Username"&&navName.getText().toString()!="Name"&&navEmail.getText().toString()!=""&&navName.getText().toString()!=""){
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.login).setVisible(false);}
        database = new BookingCinemaDatabase(this);
        database.open();
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        String data = preferences.getString("Data","No");
        if (data.equals("No")){
            khoitaodatabase();
            dataKhuyenMai();
            dataPhim();
            dataDichVu();
            dataPhongChieu();
            dataLichChieu();
        }
        database.close();
    }
    public void khoitaodatabase(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Data","Yes");
        editor.apply();
    }
    private void dataKhuyenMai() {
        database.insertKhuyenMai("Thành viên U22","Giảm","21/4/2021","29/4/2021",1);
        database.insertKhuyenMai("Thành viên U22","Giảm","21/4/2021","29/4/2021",1);
        database.insertKhuyenMai("Thành viên U22","Giảm","21/4/2021","29/4/2021",1);
    }

    public void dataDichVu(){
        database.insertDichVu("Bỏng ngô",20000);
        database.insertDichVu("Nước ngọt có ga",20000);
        database.insertDichVu("Combo nước ngọt & bỏng ngô",30000);
    }
    public void dataPhim(){
        database.insertPhim("Thiên thần hộ mệnh","Cái chết của một cô ca sĩ nổi tiếng dẫn đến sự thành công cào đến sự giúp đỡ của một thiên thần hộ mệnh?","C13","30/4/2021","Tâm lý","124 phút", R.drawable.tthm);
        database.insertPhim("Thám tử lừng danh Conan", "Thế vận hội thể thao lớn nhất thế giới được tổ chức tại Tokyo, Nhật Bản thu hút rất nhiều sự chú ý. Khi sự kiện ra mắt con tàu siêu tốc với tốc độ 1000km/h diễn ra cũng là lúc hàng loạt các vụ " +
                "bắt cóc xảy ra! Gia tộc hiểm ác tụ tập tại đây sẽ gây ra một loạt sự kiện chấn động thế giới!","P","23/4/2021","Hành động","101 phút",R.drawable.conan);;
        database.insertPhim("Bàn tay của  quỉ", "Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – " +
                "tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành 'người được chọn'","C18","23/4/2021","kinh dịch","96 phút",R.drawable.bantay);
        database.insertPhim("FAST AND FARIOUS 9", "Mắc kẹt trong một vòng lặp thời gi","C13","25/4/2021","Hành động","113 phút",R.drawable.ff);
    }

    public void dataPhongChieu(){
        database.insertPhongChieu("PC01",10,12);
        database.insertPhongChieu("PC02",10,12);
        database.insertPhongChieu("PC03",10,12);
    }

    public void dataLichChieu(){
        database.insertLichChieu(1,1,"2D","17:00","05/06/2021",45000);
        database.insertLichChieu(1,2,"2D","19:00","05/06/2021",45000);
        database.insertLichChieu(1,3,"2D","20:00","05/06/2021",45000);
        database.insertLichChieu(1,2,"2D","21:00","05/06/2021",45000);
        database.insertLichChieu(2,1,"2D","17:00","05/06/2021",45000);
        database.insertLichChieu(2,2,"2D","20:00","06/06/2021",45000);
        database.insertLichChieu(3,3,"2D","13:00","05/06/2021",45000);
        database.insertLichChieu(3,3,"2D","17:00","05/06/2021",45000);
        database.insertLichChieu(4,2,"2D","18:00","05/06/2021",45000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.ticket:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Ticketfragment()).commit();
                break;
        }
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                getSupportActionBar().setTitle("Trang chủ");
                break;
            case R.id.movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Showtimefragment()).commit();
                getSupportActionBar().setTitle("Lịch chiếu");
                break;
            case R.id.store:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Ticketfragment()).commit();
                getSupportActionBar().setTitle("Vé");
                break;
            case R.id.person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Accountfragment()).commit();
                getSupportActionBar().setTitle("Thông tin tài khoản");
                break;
            case R.id.information:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Informationfragment()).commit();
                getSupportActionBar().setTitle("Thông tin & điều khoản");
                Toast.makeText(this,"Thông tin & điều khoản",Toast.LENGTH_SHORT).show();
                break;
            case R.id.introduction:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Introducefragment()).commit();
                Toast.makeText(this,"Giới thiệu",Toast.LENGTH_SHORT).show();
                getSupportActionBar().setTitle("Giới thiệu");
                break;
            case R.id.promotion:
                getSupportActionBar().setTitle("Khuyến mãi");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Promotionfragment()).commit();
                break;
            case R.id.login:
                getSupportActionBar().setTitle("Đăng nhập");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
                break;
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                TextView navName = headerView.findViewById(R.id.nav_name);
                TextView navEmail = headerView.findViewById(R.id.nav_mail);
                ImageView navImg = headerView.findViewById(R.id.nav_img);
                navEmail.setText("Username");
                navName.setText("Name");
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.login).setVisible(true);
                Toast.makeText(this,"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editorUser = sharedPreferences.edit();
                editorUser.putString(getString(R.string.isLogin), "false");
                editorUser.commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
    public void updateNav() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navName = headerView.findViewById(R.id.nav_name);
        TextView navEmail = headerView.findViewById(R.id.nav_mail);
        ImageView navImg = headerView.findViewById(R.id.nav_img);
        if (name!="" && username!="") {
            navEmail.setText(username);
            navName.setText(name);
        }
    }
}