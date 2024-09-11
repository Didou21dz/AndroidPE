package jkas.androidpe.resourcesUtils.attrs.menu;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import jkas.androidpe.resourcesUtils.attrs.AllAttrBase;

public class AttrBase {
    public static Map<String, String[]> attrs = new HashMap<>();

    static {
        initBase();
    }

    private static void initBase() {
        attrs.clear();
        attrs.put("android:id", new String[] {"@string"});
        attrs.put("android:visible", new String[] {"true", "false"});
        attrs.put("android:enabled", new String[] {"true", "false"});
        attrs.put(
                "android:menuCategory",
                new String[] {"container", "system", "secondary", "alternative"});
        attrs.put("android:orderInCategory", new String[] {"@integer"});
    }
}
