package com.example.illusionmller_lyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class AfficherResultats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_resultats);
        TextView textViewReponses = findViewById(R.id.textView_reponses);
        TextView textViewAttendu = findViewById(R.id.textView_attendu);
        TextView textViewIndex = findViewById(R.id.textView_index);
        textViewIndex.setText((textViewIndex.getText()+"\n"));
        textViewReponses.setText(textViewReponses.getText()+"\n");
        textViewAttendu.setText(textViewAttendu.getText()+"\n");
        ArrayList<String> reponses = getIntent().getStringArrayListExtra("reponses");
        ArrayList<String> attendu = getIntent().getStringArrayListExtra("attendu");
        for (int i=0;i<reponses.size();i++){
            textViewIndex.setText(textViewIndex.getText()+String.valueOf(i+1)+"\n");
            textViewReponses.setText(textViewReponses.getText()+reponses.get(i)+"\n");
            textViewAttendu.setText(textViewAttendu.getText()+attendu.get(i)+"\n");
        }
    }
}
