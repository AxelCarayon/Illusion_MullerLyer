package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Menu_Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu__principal);
    }
}
