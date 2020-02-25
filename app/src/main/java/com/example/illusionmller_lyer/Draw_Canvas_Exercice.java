package com.example.illusionmller_lyer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Draw_Canvas_Exercice extends View {
    private Paint paint;
    private double coeffReducTaille;
    private int tourMax;
    private Random r;
    private ArrayList<Double> valeurs;
    private ArrayList<String> reponses;
    private ArrayList<String> attendu;
    private int tour;
    private double reference;
    private long seed;
    private boolean secondeSerie;

    public Draw_Canvas_Exercice(Context context, double taille, long seed, int nbRepetitions) {
        super(context);
        attendu = new ArrayList<>();
        reponses = new ArrayList<>();
        tourMax = nbRepetitions;
        reference = taille;
        if (taille>1) {
            coeffReducTaille = 1;
        }else{
            coeffReducTaille = taille;
        }
        r = new Random();
        if (seed != 0){
            this.seed = seed;
            r.setSeed(seed);
        }else{
            this.seed = r.nextLong();
        }
        initValeurs(nbRepetitions);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);
    }

    private void initValeurs(int nbRepetitions){
        tour = 0;
        valeurs = new ArrayList<>();
        if (secondeSerie){
            r.setSeed(seed);
            tour = -1;
        }

        for (int i = 1; i < nbRepetitions/2+1; i++) {
            valeurs.add(round(reference-(0.03)*i,2));
            valeurs.add(round(reference+(0.03)*i,2));
        }
        if (nbRepetitions%2 == 1) {
            valeurs.add(reference);
        }

        if (secondeSerie){
            Collections.shuffle(valeurs,new Random(r.nextLong()));
        }else {
            Collections.shuffle(valeurs, r);
        }

    }

    public void envoyerReponse(int rep){
        if (this.valeurs.get(tour) == this.reference){
            attendu.add("Egal");
        }
        else{
            if (this.valeurs.get(tour) < this.reference){
                this.attendu.add("Plus");
            }else{
                this.attendu.add("Moins");
            }
        }
        switch(rep) {
            case 0:
                this.reponses.add("Egal");
                break;
            case 1:
                this.reponses.add("Plus");
                break;
            case -1:
                this.reponses.add("Moins");
                break;

        }

        if (!secondeSerie && this.tour==tourMax-1){
            secondeSerie = true;
            this.initValeurs(tourMax);
        }
        tour++;
    }

    public ArrayList<String> getReponses(){
        return reponses;
    }

    public ArrayList<String> getAttendu(){
        return attendu;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //Calcul coordonnées========================================================================
        int yMax = canvas.getHeight();
        int xMax = canvas.getWidth();
        int xCentre = xMax/2;
        int yCentre = yMax/2;
        int Xref_debut = (int) Math.round(xCentre+(xCentre*coeffReducTaille)/2);
        int Xref_fin = (int) Math.round(xMax-(xCentre*coeffReducTaille)/2);
        int xRand_debut = (int) Math.round(0+(xCentre*valeurs.get(tour))/2);
        int xRand_fin = (int) Math.round(xCentre-(xCentre*valeurs.get(tour))/2);
        //==========================================================================================

        canvas.drawLine(xRand_debut,yCentre,xRand_fin,yCentre,paint); // dessin ligne
        canvas.drawLine(Xref_debut,yCentre,Xref_fin,yCentre,paint); // dessin reference

        if (secondeSerie){
            //dessin accolades
            canvas.drawLine(xRand_debut,yCentre,xRand_debut-60,yCentre-60,paint);
            canvas.drawLine(xRand_debut,yCentre,xRand_debut-60,yCentre+60,paint);
            canvas.drawLine(xRand_fin,yCentre,xRand_fin+60,yCentre-60,paint);
            canvas.drawLine(xRand_fin,yCentre,xRand_fin+60,yCentre+60,paint);

        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
