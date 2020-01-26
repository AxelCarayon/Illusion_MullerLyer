package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        SeekBar nombre_essais = findViewById(R.id.seekbar_nb_essais); //seekbar exercice
        SeekBar nombre_essais_entrainement = findViewById(R.id.seekbar_nb_essais_entrainement); //seekbar entrainement
        final TextView valeur_nb_essais_exercice = findViewById(R.id.textView_nb_essais_valeur);
        final TextView valeur_nb_essais_entrainement = findViewById(R.id.textView_nb_essais_valeur_entrainement);

        //listener seekbar exercice
        nombre_essais.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                valeur_nb_essais_exercice.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //listener seekbar entrainement
        nombre_essais_entrainement.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                valeur_nb_essais_entrainement.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}

