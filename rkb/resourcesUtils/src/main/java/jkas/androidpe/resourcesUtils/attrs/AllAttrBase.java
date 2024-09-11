package jkas.androidpe.resourcesUtils.attrs;

import java.util.ArrayList;
import java.util.Map;

public class AllAttrBase {
    public static final String[] VALUES_BOOLEAN = {"true", "false"};
    public static final String[] VALUES_GRAVITY = {
        "left",
        "top",
        "right",
        "bottom",
        "center",
        "center_horizontal",
        "center_vertical",
        "clip_horizontal",
        "clip_vertical",
        "start",
        "end",
        "fill",
        "fill_horizontal",
        "fill_vertical"
    };
    public static final String[] VALUES_TINT_MODE = {
        "src_over",
        "src_in",
        "src_out",
        "src_atop",
        "multiply",
        "screen",
        "add",
        "darken",
        "lighten"
    };

    public static ArrayList<String> getAttrs(Map<String, String[]> attrsBase) {
        final ArrayList<String> list = new ArrayList<>();
        for (Map.Entry entry : attrsBase.entrySet()) list.add(entry.getKey().toString());
        return list;
    }

    public static String[] getValuesOfAttr(Map<String, String[]> attrsBase, String attr) {
        if (!attrsBase.containsKey(attr.intern())) return null;
        return attrsBase.get(attr.intern());
    }
}
