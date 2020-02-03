package com.example.illusionmller_lyer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class Draw_Canvas_Exercice extends View {
    private Paint paint;
    private double coeffReducTaille;
    private Random r;

    private boolean secondeSerie;

    public Draw_Canvas_Exercice(Context context, double taille, long seed) {
        super(context);

    }

    @Override
    public void onDraw(Canvas canvas) {
        int yMax = canvas.getHeight();
        int xMax = canvas.getWidth();
        int xCentre = xMax/2;
        int yCentre = yMax/2;

        int Xref_debut = (int) Math.round(xCentre+(xCentre*coeffReducTaille)/2);
        int Xref_fin = (int) Math.round(xMax-(xCentre*coeffReducTaille)/2);

        double randomValue = 0.1 + (0.9 - 0.1) * r.nextDouble();
        int xRand_debut = (int) Math.round(0+(xCentre*randomValue)/2);
        int xRand_fin = (int) Math.round(xCentre-(xCentre*randomValue)/2);

        canvas.drawLine(Xref_debut,yCentre,Xref_fin,yCentre,paint); // reference

        canvas.drawText("Référence",xCentre+1,50,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(xMax/2, 0, xMax/2, yMax, paint);
        paint.setColor(Color.BLACK);
    }


}
