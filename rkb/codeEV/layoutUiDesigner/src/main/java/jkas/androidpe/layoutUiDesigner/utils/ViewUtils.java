package jkas.androidpe.layoutUiDesigner.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import jkas.androidpe.layoutUiDesigner.R;
import jkas.androidpe.layoutUiDesigner.palette.DefaultView;
import jkas.androidpe.layoutUiDesigner.tools.ViewCreator;

/**
 * @author JKas
 */
public class ViewUtils {
    public static void drawDashPathStroke(View view) {
        if (CurrentSettings.isDrawStrokeEnabled)
            drawDashPath(view, view.getContext().getDrawable(R.drawable.draw_dash));
    }

    public static void drawDashPathStrokeSelected(View view) {
        if (CurrentSettings.isDrawStrokeEnabled)
            drawDashPath(view, view.getContext().getDrawable(R.drawable.draw_dash_selected));
    }

    public static void drawDashPath(View view, Drawable drawable) {
        Drawable foreground = view.getForeground();
        if (foreground != null) {
            if (foreground instanceof LayerDrawable) {
                LayerDrawable oldLayer = (LayerDrawable) foreground;
                if (oldLayer.getNumberOfLayers() == 1) {
                    LayerDrawable layer = new LayerDrawable(new Drawable[] {drawable});
                    view.setForeground(layer);
                } else {
                    LayerDrawable layer =
                            new LayerDrawable(new Drawable[] {oldLayer.getDrawable(0)});
                    layer.addLayer(drawable);
                    view.setForeground(layer);
                }
            } else {
                LayerDrawable layer = new LayerDrawable(new Drawable[] {foreground});
                layer.addLayer(drawable);
                view.setForeground(layer);
            }
        } else {
            LayerDrawable layer = new LayerDrawable(new Drawable[] {drawable});
            view.setForeground(layer);
        }
    }

    public static void setBgCornerRadius(View v, int color) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadius(43f);
        ColorStateList csl = ColorStateList.valueOf(Color.GRAY);
        RippleDrawable rd = new RippleDrawable(csl, gd, null);
        v.setBackground(rd);
    }

    public static void setBgCornerRadius(View v, int color, float cornerRadius) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadius(cornerRadius);
        ColorStateList csl = ColorStateList.valueOf(Color.GRAY);
        RippleDrawable rd = new RippleDrawable(csl, gd, null);
        v.setBackground(rd);
    }

    public static boolean isParentView(@NonNull View view) {
        return view instanceof ViewGroup;
    }

    public static boolean isParentView(Context context, @NonNull String pkg) {
        View view = ViewCreator.create("", context, pkg, false);
        if (!(view instanceof DefaultView)) return (view instanceof ViewGroup);
        return false;
    }

    public static class CurrentSettings {
        public static boolean isDrawStrokeEnabled = true;
        public static boolean addByDrag = false;
    }
}
