package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Consigne_Entrainement extends AppCompatActivity {

    private Button button_commencer;
    private int temps;
    private int entrainement;
    private int exercice;
    private TextView consigne;
    private boolean suite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consigne_entrainement);

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

        lireOptions();

        button_commencer = findViewById(R.id.button_commencer_entrainement);
        consigne = findViewById(R.id.textView_exeplication_entrainement);
        consigne.setText(consigne.getText().toString().replace("@TEMPS@",""+temps));
        if (temps <2){
            consigne.setText(consigne.getText().toString().replace("secondes","seconde"));
        }
        suite = false ;

        button_commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suite) {
                    openNewActivity(Experience_Entrainement.class);
                }else{
                    suite = true;
                    consigne.setText(R.string.consignesEntrainementSuite);
                    String texte = consigne.getText().toString();
                    texte = texte.replace("@ENTRAINEMENT@",""+entrainement);
                    texte = texte.replace("@EXERCICE@",""+exercice);
                    consigne.setText(texte);

                    button_commencer.setText("Commencer");
                }
            }
        });



    }
    public void openNewActivity(Class activite) {
        Intent intent = new Intent(this, activite);
        intent.putExtra("entrainement",true);
        startActivity(intent);
        finish();
    }

    private int[] lireOptions(){
        ReadWrite_File file = new ReadWrite_File("/options.properties");
        int[] res = new int[4];
        try{
            entrainement = Integer.parseInt(file.read()[0]);
            exercice = Integer.parseInt(file.read()[1]);
            temps = Integer.parseInt(file.read()[3]);
        }catch (Exception e){
            entrainement = 5;
            exercice = 5;
            temps = 3;
        }
        return res;
    }
}
