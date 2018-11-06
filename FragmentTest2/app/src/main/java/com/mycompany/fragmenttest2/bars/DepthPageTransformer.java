package com.mycompany.fragmenttest2.bars;


import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) {
            // pagina hacia la izquierda, no visible
            view.setAlpha(0);

        } else if (position <= 0) {
            // usa la transicion por defecto al hacer swipe a la izquierda
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) {
            // agregar fading (cambiar opacidad)
            view.setAlpha(1 - position);

            view.setTranslationX(pageWidth * -position);

            // escala la view, la hace mas chica
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else {
            // pagina hacia la derecha, no visible
            view.setAlpha(0);
        }
    }
}