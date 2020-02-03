package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Experience_Exercice extends AppCompatActivity {

    private Button button_egal;
    private Button button_plus;
    private Button button_moins;
    private FrameLayout canvas;
    private Draw_Canvas_Exercice draw_canvasExercice;
    private int nbRepetitions;
    private long seed;
    private boolean secondeSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialisations ==========================================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_exercice);
        final int[] options = lireOptions();
        nbRepetitions = options[0];
        seed = options[1];
        secondeSerie = false;
        draw_canvasExercice = new Draw_Canvas_Exercice(this,0.6,seed,nbRepetitions);
        canvas = findViewById(R.id.canvas_exercice);
        canvas.addView(draw_canvasExercice);
        button_plus=findViewById(R.id.button_plus_exo);
        button_moins=findViewById(R.id.button_exo_moins);
        button_egal=findViewById(R.id.button_egal_exo);
        //==========================================================================================
        button_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                draw_canvasExercice.envoyerReponse(-1);
                if (nbRepetitions == 0){
                    if (secondeSerie) {
                        activiteSuivante(Menu_Principal.class);
                    }
                    else{
                        nbRepetitions = options[0];
                        secondeSerie = true;
                        draw_canvasExercice.invalidate();
                    }
                }else{
                    draw_canvasExercice.invalidate();
                }
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                draw_canvasExercice.envoyerReponse(1);
                if (nbRepetitions == 0){
                    if (secondeSerie) {
                        activiteSuivante(Menu_Principal.class);
                    }
                    else{
                        nbRepetitions = options[0];
                        secondeSerie = true;
                        draw_canvasExercice.invalidate();
                    }
                }else{
                    draw_canvasExercice.invalidate();
                }
            }
        });

        button_egal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                draw_canvasExercice.envoyerReponse(0);
                if (nbRepetitions == 0){
                    if (secondeSerie) {
                        activiteSuivante(Menu_Principal.class);
                    }
                    else{
                        nbRepetitions = options[0];
                        secondeSerie = true;
                        draw_canvasExercice.invalidate();
                    }
                }else{
                    draw_canvasExercice.invalidate();
                }
            }
        });
    }

    private int[] lireOptions(){
        ReadWrite_File file = new ReadWrite_File("/options.properties");
        int[] res = new int[2];
        try{
            res[0] = Integer.parseInt(file.read()[1]);
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
