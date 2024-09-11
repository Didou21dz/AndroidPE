package jkas.androidpe.resourcesUtils.attrs.layout.widgets;

import java.util.Map;
import java.util.HashMap;
import static jkas.androidpe.resourcesUtils.attrs.AllAttrBase.VALUES_BOOLEAN;

/**
 * @author JKas
 */
public class AttrImageView {
    public static Map<String, String[]> attrs = new HashMap<>();

    static {
        attrs.clear();
        init();
    }

    private static void init() {
        attrs.put("android:adjustViewBounds", VALUES_BOOLEAN);
        attrs.put("android:baseline", new String[] {"@dimen"});
        attrs.put("android:baselineAlignBottom", VALUES_BOOLEAN);
        attrs.put("android:cropToPadding", VALUES_BOOLEAN);
        attrs.put("android:maxHeight", new String[] {"@dimen"});
        attrs.put("android:maxWidth", new String[] {"@dimen"});
        attrs.put(
                "android:scaleType",
                new String[] {
                    "center",
                    "centerCrop",
                    "centerInside",
                    "fitCenter",
                    "fitEnd",
                    "fitStart",
                    "fitXY",
                    "matrix"
                });

        attrs.put("android:src", new String[] {"@drawable"});
        attrs.put("android:tint", new String[] {"@color"});
        attrs.put(
                "android:intMode",
                new String[] {"add", "multiply", "screen", "src_atop", "src_in", "src_over"});
    }
}
