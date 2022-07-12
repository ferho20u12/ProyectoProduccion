package com.example.proyectoserviciosocial22b;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.main_container, new HomeFragment(), "");
        ft.commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            switch (item.getItemId()){
                case R.id.nav_home:
                    ft1.replace(R.id.main_container, new HomeFragment(), "");
                    ft1.commit();
                    break;
                case R.id.nav_detector:
                    ft1.replace(R.id.main_container, new DetectorFragment(), "");
                    ft1.commit();
                    break;
                case R.id.nav_library:
                    ft1.replace(R.id.main_container, new LibraryFragment(),"");
                    ft1.commit();
                    break;
            }
            return true;
        });
    }
    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}