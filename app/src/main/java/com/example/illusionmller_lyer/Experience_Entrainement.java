package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Experience_Entrainement extends AppCompatActivity {

    private Button button_egal;
    private Button button_plus;
    private Button button_moins;
    private FrameLayout canvas;
    private Draw_Canvas_Entrainement draw_canvasEntrainement;
    private int nbRepetitions;
    private long seed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_entrainement);

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);

        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {

                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        int[] options = lireOptions();
        nbRepetitions = options[0];
        seed = options[1];
        draw_canvasEntrainement = new Draw_Canvas_Entrainement(this,0.6,seed);
        canvas = findViewById(R.id.frame_canvas);
        canvas.addView(draw_canvasEntrainement);

        button_egal = findViewById(R.id.button_egal);
        button_plus = findViewById(R.id.button_moins_grand);
        button_moins = findViewById(R.id.button_plus_grand);

        button_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                if (nbRepetitions == 0){
                    activiteSuivante(Consigne_Exercice.class);
                }else{
                    draw_canvasEntrainement.invalidate();
                }
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                if (nbRepetitions == 0){
                    activiteSuivante(Consigne_Exercice.class);
                }else{
                    draw_canvasEntrainement.invalidate();
                }
            }
        });

        button_egal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                if (nbRepetitions == 0){
                    activiteSuivante(Consigne_Exercice.class);
                }else{
                    draw_canvasEntrainement.invalidate();
                }
            }
        });
    }

    private int[] lireOptions(){
        ReadWrite_File file = new ReadWrite_File("/options.properties");
        int[] res = new int[2];
        try{
         res[0] = Integer.parseInt(file.read()[0]);
         res[1] = Integer.parseInt(file.read()[2]);
        }catch (Exception e){
            res[0] = 5;
            res[1] = 0;
        }
        return res;
    }

    private void activiteSuivante(Class activite){
        Intent intent = new Intent(this, activite);
        startActivity(intent);
        finish();
    }
}
