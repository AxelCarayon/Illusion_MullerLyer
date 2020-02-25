package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Experience_ExerciceSuite extends AppCompatActivity {
    private Handler handler;
    private boolean visible;
    private Button button_egal;
    private Button button_plus;
    private Button button_moins;
    private FrameLayout canvas;
    private Draw_Canvas_ExerciceSuite draw_canvasExercice;
    private int nbRepetitions;
    private long seed;
    //private boolean secondeSerie;
    private int duree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialisations ==========================================================================
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience__exercice_suite);

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);

        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        final int[] options = lireOptions();
        nbRepetitions = options[0];
        seed = options[1];
        duree = options[2];
        draw_canvasExercice = new Draw_Canvas_ExerciceSuite(this, 0.6, seed, nbRepetitions);
        canvas = findViewById(R.id.canvas_exercice_suite);
        canvas.addView(draw_canvasExercice);
        button_plus = findViewById(R.id.button_exo_plus_suite);
        button_moins = findViewById(R.id.button_exo_moins_suite);
        button_egal = findViewById(R.id.button_exo_egal_suite);
        handler = new Handler();
        timer(duree);
        //==========================================================================================
        button_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                draw_canvasExercice.envoyerReponse(-1);
                if (nbRepetitions == 0) {
                    activiteSuivante(AfficherResultats.class);
                    nbRepetitions = options[0];
                } else {
                    timer(duree);
                }
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                draw_canvasExercice.envoyerReponse(1);
                if (nbRepetitions == 0) {
                    activiteSuivante(AfficherResultats.class);
                    nbRepetitions = options[0];
                } else {
                    timer(duree);
                }
            }
        });

        button_egal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbRepetitions--;
                draw_canvasExercice.envoyerReponse(0);
                if (nbRepetitions == 0) {
                    activiteSuivante(AfficherResultats.class);
                    nbRepetitions = options[0];
                } else {
                    timer(duree);
                }
            }
        });
    }

    private int[] lireOptions() {
        ReadWrite_File file = new ReadWrite_File("/options.properties");
        int[] res = new int[3];
        try {
            res[0] = Integer.parseInt(file.read()[1]);
            res[1] = Integer.parseInt(file.read()[2]);
            res[2] = Integer.parseInt(file.read()[3]);
        } catch (Exception e) {
            res[0] = 5;
            res[1] = 0;
            res[2] = 3;
        }
        return res;
    }

    private void activiteSuivante(Class activite) {
        Intent intent = new Intent(this, activite);
        Intent precedent = getIntent();
        intent.putExtra("reponsesNoIllusion",precedent.getStringArrayListExtra("reponsesNoIllusion"));
        intent.putExtra("attenduNoIllusion",precedent.getStringArrayListExtra("attenduNoIllusion"));
        intent.putExtra("reponsesWithIllusion",draw_canvasExercice.getReponses());
        intent.putExtra("attenduWithIllusion",draw_canvasExercice.getAttendu());
        startActivity(intent);
        finish();
    }

    private void timer(int ms) {
        handler.removeCallbacksAndMessages(null);
        int interval = ms * 1000; // 1 Second
        if (visible) {
            draw_canvasExercice.invalidate();
        }
        Runnable runnable = new Runnable() {
            public void run() {
                canvas.setVisibility(View.GONE);
                visible = false;
            }
        };
        handler.postAtTime(runnable, System.currentTimeMillis() + interval);
        handler.postDelayed(runnable, interval);
        canvas.setVisibility(View.VISIBLE);
        visible = true;

    }
}
