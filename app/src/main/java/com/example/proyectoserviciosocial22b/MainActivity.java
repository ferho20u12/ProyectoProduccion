package com.example.proyectoserviciosocial22b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
//owns
import classes.Message;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Message toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = new Message(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.main_container, new HomeFragment(), "");
        ft.commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                switch (item.getItemId()){
                    case R.id.nav_home:
                        ft.replace(R.id.main_container, new HomeFragment(), "");
                        ft.commit();
                        break;
                    case R.id.nav_detector:
                        ft.replace(R.id.main_container, new DetectorFragment(), "");
                        ft.commit();
                        break;
                    case R.id.nav_library:
                        ft.replace(R.id.main_container, new LibraryFragment(),"");
                        ft.commit();
                        break;
                }
                return true;
            }
        });
    };
}