package com.example.bookingmovie;

import androidx.annotation.NonNull;
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
import com.example.bookingmovie.Database.DatabaseUser;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private  Toolbar toolbar;
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

        getSupportActionBar().setTitle(null);
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
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        return true;
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Showtimefragment()).commit();
                break;
            case R.id.ticket:
                Toast.makeText(this,"Vé của bạn",Toast.LENGTH_SHORT).show();
                break;
            case R.id.person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Accountfragment()).commit();
                break;
            case R.id.share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Accountfragment()).commit();
                break;
            case R.id.promotion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Promotionfragment()).commit();
                break;
            case R.id.login:
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
        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String mail = preferences.getString(getString(R.string.user),"");
        String name = preferences.getString(getString(R.string.name),"");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editorUser = sharedPreferences.edit();
        editorUser.putString(getString(R.string.user), username);
        editorUser.commit();
        editorUser.putString(getString(R.string.name), name);
        editorUser.commit();*/

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