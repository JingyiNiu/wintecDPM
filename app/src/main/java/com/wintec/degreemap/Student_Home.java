package com.wintec.degreemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Student_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // set on selected listener
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // set the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Student_Dashboard_Fragment()).commit();
    }

    // bottom navigation item selected listener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_dashboard:
                            selectedFragment = new Student_Dashboard_Fragment();
                            break;
                        case R.id.nav_course:
                            selectedFragment = new Student_Course_Fragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new Student_Profile_Fragment();
                            break;
                    }

                    // apply the change of fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
}