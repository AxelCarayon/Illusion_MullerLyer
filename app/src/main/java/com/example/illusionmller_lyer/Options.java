package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Options extends AppCompatActivity {

    Button button_valider;
    int nb_entrainement;
    int nb_exercices;
    long seed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        //instantiation valeurs ====================================================================
        button_valider = findViewById(R.id.button_valider_options); //bouton valider
        SeekBar nombre_essais = findViewById(R.id.seekbar_nb_essais); //seekbar exercice
        SeekBar nombre_essais_entrainement = findViewById(R.id.seekbar_nb_essais_entrainement); //seekbar entrainement
        final TextView valeur_nb_essais_exercice = findViewById(R.id.textView_nb_essais_valeur); //valeur exo
        final TextView valeur_nb_essais_entrainement = findViewById(R.id.textView_nb_essais_valeur_entrainement); //valeur entrainement
        final EditText text_seed = findViewById(R.id.editText_seed); //valeur seed

        seed = 0;
        nb_entrainement = 0;
        nb_exercices = 0;
        //==========================================================================================

        //listener seekbar exercice ================================================================
        nombre_essais.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                valeur_nb_essais_exercice.setText(String.valueOf(progress));
                nb_exercices = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //==========================================================================================

        //listener bouton valider ==================================================================
        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadWrite_File file = new ReadWrite_File("/options.properties");
                file.write(nb_entrainement,nb_exercices,seed);
                String[] res = file.read();
                Log.i("entrainement",""+res[0]);
                Log.i("exercice",""+res[1]);
                Log.i("seed",""+res[2]);
            }
        });
        //==========================================================================================

        //listener seekbar entrainement ============================================================
        nombre_essais_entrainement.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                valeur_nb_essais_entrainement.setText(String.valueOf(progress));
                nb_entrainement = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //==========================================================================================
    }
}

