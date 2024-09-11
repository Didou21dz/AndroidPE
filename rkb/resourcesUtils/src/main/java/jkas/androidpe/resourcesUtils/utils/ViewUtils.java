package jkas.androidpe.resourcesUtils.utils;

import android.content.Context;
import android.view.View;
import android.graphics.Bitmap;
import java.io.File;
import java.util.Random;
import java.io.FileOutputStream;
import android.graphics.drawable.GradientDrawable;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;

/**
 * @author JKas
 */
public class ViewUtils {

    public static void setBackgroundRipple(View v, int colorGradient, int colorRipple) {
        setBackgroundRipple(v, colorGradient, colorRipple, 43);
    }

    public static void setBackgroundRipple(View v, int colorG, int colorR, float cornerR) {
        GradientDrawable gradient = new GradientDrawable();
        gradient.setColor(colorG);
        gradient.setCornerRadius(cornerR);
        ColorStateList colorState = ColorStateList.valueOf(colorR);
        RippleDrawable ripple = new RippleDrawable(colorState, gradient, null);
        v.setBackground(ripple);
    }
}
