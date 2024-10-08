package jkas.androidpe.resourcesUtils.attrs.layout.material3;

import java.util.Map;
import java.util.HashMap;
import jkas.androidpe.resourcesUtils.attrs.layout.widgets.AttrTextView;

/**
 * @author JKas
 */
public class AttrExtendedFloatingActionButton {
    public static Map<String, String[]> attrs = new HashMap<>();

    static {
        attrs.clear();
        attrs.putAll(AttrFloatingActionButton.attrs);
        attrs.putAll(AttrTextView.attrs);
        init();
    }

    private static void init() {
        // nothing else.
    }
}
