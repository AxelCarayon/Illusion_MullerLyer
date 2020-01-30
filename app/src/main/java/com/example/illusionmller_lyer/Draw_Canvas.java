package com.example.illusionmller_lyer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class Draw_Canvas extends View {
    private Paint paint;
    private int[] nb_traits;
    private int[] compteur;
    private double coeffReducTaille;

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);
    }
    public Draw_Canvas(Context context,double t) {
        super(context);
        if (t>1) {
            coeffReducTaille = 1;
        }else{
            coeffReducTaille = t;
        }
        init();
    }
    public Draw_Canvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint.reset();
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        int yMax = canvas.getHeight();
        int xMax = canvas.getWidth();
        int xCentre = xMax/2;
        int yCentre = yMax/2;

        int Xref_debut = (int) Math.round(xCentre+(xCentre*coeffReducTaille)/2);
        int Xref_fin = (int) Math.round(xMax-(xCentre*coeffReducTaille)/2);
        Random r = new Random();
        double randomValue = 0.1 + (0.9 - 0.1) * r.nextDouble();
        int xRand_debut = (int) Math.round(0+(xCentre*randomValue)/2);
        int xRand_fin = (int) Math.round(xCentre-(xCentre*randomValue)/2);

        canvas.drawLine(xRand_debut,yCentre,xRand_fin,yCentre,paint);
        canvas.drawLine(Xref_debut,yCentre,Xref_fin,yCentre,paint);

        canvas.drawText("Référence",xCentre+1,50,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(xMax/2, 0, xMax/2, yMax, paint);
        paint.setColor(Color.BLACK);

    }
}
