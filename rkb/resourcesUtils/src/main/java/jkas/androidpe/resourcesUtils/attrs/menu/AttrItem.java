package jkas.androidpe.resourcesUtils.attrs.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jkas.androidpe.resourcesUtils.attrs.AllAttrBase;

public class AttrItem {
    public static Map<String, String[]> attrs = new HashMap<>();

    static {
        attrs.putAll(AttrBase.attrs);
        init();
    }

    public static ArrayList<String> getAttrs() {
        return AllAttrBase.getAttrs(attrs);
    }

    public static String[] getValuesOfAttr(String attrName) {
        return AllAttrBase.getValuesOfAttr(attrs, attrName);
    }

    private static void init() {
        attrs.put("android:title", new String[] {"@string"});
        attrs.put("android:titleCondensed", new String[] {"@string"});
        attrs.put("android:icon", new String[] {"@drawable"});
        attrs.put("android:onClick", null);
        attrs.put("android:title", new String[] {"@string"});
        attrs.put("android:titleCondensed", new String[] {"@string"});
        attrs.put("android:icon", new String[] {"@drawable"});
        attrs.put(
                "android:showAsAction",
                new String[] {"ifRoom", "never", "withText", "always", "collapseActionView"});
        attrs.put("android:actionLayout", new String[] {"@layout"});
        attrs.put("android:actionViewClass", null);
        attrs.put("android:actionProviderClass", null);

        attrs.put("android:alphabeticShortcut", new String[] {"@string"});
        attrs.put(
                "android:alphabeticModifiers",
                new String[] {"META", "CTRL", "ALT", "SHIFT", "SYM", "FUNCTION"});
        attrs.put("android:numericShortcut", new String[] {"@string"});
        attrs.put(
                "android:numericModifiers",
                new String[] {"META", "CTRL", "ALT", "SHIFT", "SYM", "FUNCTION"});
        attrs.put("android:checkable", new String[] {"true", "false"});
    }
}
