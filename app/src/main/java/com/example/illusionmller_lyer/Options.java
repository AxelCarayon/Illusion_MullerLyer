package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    private Button button_valider;
    private int nb_entrainement;
    private int nb_exercices;
    private long seed;
    private TextView valeur_nb_essais_exercice;
    private TextView valeur_nb_essais_entrainement;
    private ReadWrite_File file;
    private EditText text_seed;
    private SeekBar nombre_essais;
    private SeekBar nombre_essais_entrainement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        initialiserVariables();
        lectureOptions();

        //listener seekbar exercice ================================================================
        nombre_essais.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        //listener seed ============================================================================

        text_seed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    seed = Long.valueOf(text_seed.getText().toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //==========================================================================================

        //listener bouton valider ==================================================================
        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Options sauvegardées.", Toast.LENGTH_SHORT).show();
                file.write(nb_entrainement, nb_exercices, seed);
            }
        });
        //==========================================================================================

        //listener seekbar entrainement ============================================================
        nombre_essais_entrainement.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

    private void initialiserVariables(){
        file = new ReadWrite_File("/options.properties");
        button_valider = findViewById(R.id.button_valider_options); //bouton valider
        nombre_essais = findViewById(R.id.seekbar_nb_essais); //seekbar exercice
        nombre_essais_entrainement = findViewById(R.id.seekbar_nb_essais_entrainement); //seekbar entrainement
        valeur_nb_essais_exercice = findViewById(R.id.textView_nb_essais_valeur); //valeur exo
        valeur_nb_essais_entrainement = findViewById(R.id.textView_nb_essais_valeur_entrainement); //valeur entrainement
        text_seed = findViewById(R.id.editText_seed); //valeur seed

        seed = 0;
        nb_entrainement = 0;
        nb_exercices = 0;

    }

    private void lectureOptions(){
        try {
            String[] res = file.read();
            seed = Long.valueOf(res[2]);
            text_seed.setText(res[2]);
            nb_entrainement = Integer.valueOf(res[0]);
            valeur_nb_essais_entrainement.setText(res[0]);
            nombre_essais_entrainement.setProgress(nb_entrainement);
            nb_exercices = Integer.valueOf(res[1]);
            valeur_nb_essais_exercice.setText(res[1]);
            nombre_essais.setProgress(nb_exercices);

        } catch (Exception e) {
            Log.wtf("erreur lecture", "impossible de lire le fichier properties");
        }
    }

}

