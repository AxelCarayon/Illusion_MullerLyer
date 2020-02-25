package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AfficherResultats extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_resultats);

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

        Intent reponses = getIntent();
        ArrayList<String> repNoIllusion = reponses.getStringArrayListExtra("reponsesNoIllusion");
        ArrayList<String> attNoIllusion = reponses.getStringArrayListExtra("attenduNoIllusion");
        ArrayList<String> repWithIllusion = reponses.getStringArrayListExtra("reponsesWithIllusion");
        ArrayList<String> attWithIllusion = reponses.getStringArrayListExtra("attenduWithIllusion");
        linearLayout = findViewById(R.id.LinearLayout_resultats);

        TextView titre1 = new TextView(this);
        titre1.setGravity(Gravity.CENTER);
        titre1.setTextSize(30);
        titre1.setText("Série sans illusion :");

        linearLayout.addView(titre1);

        //ajout des réponses sans illusion
        for (int i = 0;i<repNoIllusion.size();i++){
            TextView ligne = new TextView(this);
            ligne.setGravity(Gravity.CENTER);
            ligne.setText(repNoIllusion.get(i));
            ligne.setTextSize(30);
            if (repNoIllusion.get(i).equals(attNoIllusion.get(i))){
                ligne.setTextColor(Color.GREEN);
            }else{
                ligne.setTextColor(Color.RED);
            }
            linearLayout.addView(ligne);
        }

        //ajout retour à la ligne
        TextView titre2 = new TextView(this);
        titre2.setGravity(Gravity.CENTER);
        titre2.setTextSize(30);
        titre2.setText("\nSérie avec illusion :");
        linearLayout.addView(titre2);


        //Ajout des réponses avec illusion
        for (int i = 0;i<repWithIllusion.size();i++){
            TextView ligne = new TextView(this);
            ligne.setGravity(Gravity.CENTER);
            ligne.setText(repWithIllusion.get(i));
            ligne.setTextSize(30);
            if (repWithIllusion.get(i).equals(attWithIllusion.get(i))){
                ligne.setTextColor(Color.GREEN);
            }else{
                ligne.setTextColor(Color.RED);
            }
            linearLayout.addView(ligne);
        }
    }
}
