package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Experience extends AppCompatActivity {

    private Button button_egal;
    private Button button_plus;
    private Button button_moins;
    private boolean est_entrainement;
    FrameLayout canvas;
    Draw_Canvas draw_canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        draw_canvas = new Draw_Canvas(this,0.6);
        canvas = findViewById(R.id.frame_canvas);
        canvas.addView(draw_canvas);
        //draw_canvas.invalidate();
        try {
            est_entrainement = getIntent().getExtras().getBoolean("entrainement");
        }catch (Exception e){
            est_entrainement = false;
        }
        Log.i("entrainement",String.valueOf(est_entrainement));
        button_egal = findViewById(R.id.button_egal);
        button_plus = findViewById(R.id.button_moins_grand);
        button_moins = findViewById(R.id.button_plus_grand);

        button_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw_canvas.invalidate();
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw_canvas.invalidate();
            }
        });

        button_egal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw_canvas.invalidate();
            }
        });
    }
}
