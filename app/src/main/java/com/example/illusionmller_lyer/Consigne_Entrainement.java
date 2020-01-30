package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Consigne_Entrainement extends AppCompatActivity {

    private Button button_commencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consigne__entrainement);

        button_commencer = findViewById(R.id.button_commencer_entrainement);

        button_commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(Experience.class);
            }
        });



    }
    public void openNewActivity(Class activite) {
        Intent intent = new Intent(this, activite);
        intent.putExtra("entrainement",true);
        startActivity(intent);
        finish();
    }
}
