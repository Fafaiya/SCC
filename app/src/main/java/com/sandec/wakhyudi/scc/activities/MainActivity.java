package com.sandec.wakhyudi.scc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.sandec.wakhyudi.scc.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainActivity extends AppCompatActivity {
BannerSlider slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        slider = (BannerSlider)findViewById(R.id.bs_main);

        int[] gambar = {R.drawable.reading,R.drawable.teach,R.drawable.grow};

        List<Banner> listBanner = new ArrayList<>();

        for (int i = 0; i <gambar.length ; i++) {
            listBanner.add(new DrawableBanner(gambar[i]));

        }
        slider.setBanners(listBanner);

    }

    public void signUp(View view) {
    }

    public void login(View view) {
        startActivity(new Intent(this,DaftarLowonganActivity.class));
    }
}
