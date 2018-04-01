package com.example.binar.binarbinariahya_1202152323_modul6;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    //inisialisasi variabel
    ViewPager vp;
    TabLayout tl;
    AppBarLayout ab;
    Toolbar tbr;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //inisialisasi semua objek
        vp = findViewById(R.id.vp);
        tl = findViewById(R.id.tabs);
        ab = findViewById(R.id.appbar);
        tbr = findViewById(R.id.tb);
        auth = FirebaseAuth.getInstance();

        //menentukan toolbar
        setSupportActionBar(tbr);
        setupPager(vp);
        //mengikat tablayout dengan viewpager
        tl.setupWithViewPager(vp);
    }
    //menetapkan adapter untuk viewpager
    public void setupPager(ViewPager v) {
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        adapter.addFragment(new fraghomeall(), "NEWEST");
        adapter.addFragment(new fraghomeuser(), "ME");

        v.setAdapter(adapter);
    }
    //method untuk membuat menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    //method saat menu logout dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.signout){
            auth.signOut();
            startActivity(new Intent(Home.this, Signin.class));
            finish();
        }
        return true;
    }

    //method saat tombol post diklik
    public void post(View view) {
        startActivity(new Intent(Home.this, Upload.class));
    }

    //subclass sebagai adapter untuk viewpager dengan fragmentnya
    class VPAdapter extends FragmentPagerAdapter{
        private final List<Fragment> listfragment = new ArrayList<>();
        private final List<String> listfragmenttitle = new ArrayList<>();

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listfragment.get(position);
        }
        public void addFragment(Fragment f, String title){
            listfragment.add(f);
            listfragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listfragmenttitle.get(position);
        }

        @Override
        public int getCount() {
            return listfragment.size();
        }
    }
}
