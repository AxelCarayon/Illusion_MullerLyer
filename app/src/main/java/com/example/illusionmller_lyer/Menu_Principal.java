package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Menu_Principal extends AppCompatActivity {

    Button button_options; //bouton options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isStoragePermissionGranted()) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu__principal);
            button_options = findViewById(R.id.button_options);


            //listener du bouton options
            button_options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNewActivity();
                }
            });
        }
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission", "Permission is granted");
                return true;
            } else {
                Log.wtf("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission", "Permission is granted");
            return true;
        }
    }
}
