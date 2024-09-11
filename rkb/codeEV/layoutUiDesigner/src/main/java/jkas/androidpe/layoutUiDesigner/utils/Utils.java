package jkas.androidpe.layoutUiDesigner.utils;

import android.view.View;
import jkas.codeUtil.Files;
import org.w3c.dom.Element;
import java.util.Map;
import java.util.ArrayList;
import androidx.core.util.Pair;
import java.util.LinkedHashMap;
import java.util.Set;
import org.w3c.dom.Node;

/**
 * @author JKas
 */
public class Utils {
    public static Node eCopied;

    public static ArrayList<Pair<?, ?>> convertMapToPairViewElement(Map<?, ?> map) {
        final ArrayList<Pair<?, ?>> listPair = new ArrayList<>();

        Set<?> keys = map.keySet();
        for (Object key : keys) {
            View view = (View) key;
            Element element = (Element) map.get(view);
            listPair.add(new Pair<>(view, element));
        }
        return listPair;
    }

    public static ArrayList<Pair<?, ?>> convertLinkedHashMapToPairViewElement(
            LinkedHashMap<?, ?> map) {
        final ArrayList<Pair<?, ?>> listPair = new ArrayList<>();

        Set<?> keys = map.keySet();
        for (Object key : keys) {
            View view = (View) key;
            Element element = (Element) map.get(view);
            listPair.add(new Pair<>(view, element));
        }
        return listPair;
    }

    public static String createTagFromFileName(String path) {
        return Files.getNameFromAbsolutePath(path) + View.generateViewId();
    }
}
