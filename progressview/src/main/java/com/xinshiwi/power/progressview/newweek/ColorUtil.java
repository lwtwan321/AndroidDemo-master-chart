package com.xinshiwi.power.progressview.newweek;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by apple on 2018/1/11.
 */

public class ColorUtil {
    public static int getRandomColor() {
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;
        return Color.parseColor("#" + r + g + b);
    }
}
