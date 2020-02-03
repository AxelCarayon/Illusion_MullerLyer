package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class Experience_Exercice extends AppCompatActivity {

    private Button button_egal;
    private Button button_plus;
    private Button button_moins;
    private boolean est_entrainement;
    private FrameLayout canvas;
    private Draw_Canvas_Exercice draw_canvasExercice;
    private int nbRepetitions;
    private long seed;
    private boolean secondeSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_exercice);
        int[] options = lireOptions();
        nbRepetitions = options[0];
        seed = options[1];
        secondeSerie = false;
        draw_canvasExercice = new Draw_Canvas_Exercice(this,0.6,seed);
        button_plus=findViewById(R.id.button_plus_exo);
        button_moins=findViewById(R.id.button_exo_moins);
        button_egal=findViewById(R.id.button_egal_exo);
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
