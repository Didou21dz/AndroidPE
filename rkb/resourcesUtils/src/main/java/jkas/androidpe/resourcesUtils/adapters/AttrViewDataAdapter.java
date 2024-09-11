package jkas.androidpe.resourcesUtils.adapters;

import java.util.Collections;
import java.util.Map;
import jkas.androidpe.resourcesUtils.bases.AttrValuesRefBase;
import jkas.androidpe.resourcesUtils.dataInitializer.DataRefManager;
import jkas.androidpe.resourcesUtils.modules.ModuleRes;
import java.util.ArrayList;

/**
 * @author JKas
 */
public class AttrViewDataAdapter {
    public static ArrayList<String> getListAssist(String valueType) {
        if (valueType == null) return new ArrayList<>();
        return loadData(valueType);
    }

    private static ArrayList<String> loadData(String valueType) {
        ArrayList<String> listAdapter = new ArrayList<>();
        ModuleRes mr = DataRefManager.getInstance().currentModuleRes;
        listAdapter.clear();
        if (valueType.equals("@string")) {
            for (Map.Entry entry : mr.valuesStrings.entrySet())
                listAdapter.add("@string/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:string/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@string/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@string/")) listAdapter.add(ref);
        } else if (valueType.equals("@color") || valueType.contains("color")) {
            for (Map.Entry entry : mr.valuesColors.entrySet())
                listAdapter.add("@color/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid) {
                if (ref.startsWith("@android:color/")) listAdapter.add(ref);
                if (ref.matches("\\?[a-zA-Z0-9]")) listAdapter.add(ref);
            }
            for (String ref : AttrValuesRefBase.listRefAndroidX) {
                if (ref.startsWith("@color/")) listAdapter.add(ref);
                if (ref.matches("\\?[a-zA-Z0-9]")) listAdapter.add(ref);
            }
            for (String ref : AttrValuesRefBase.listRefMaterial3) {
                if (ref.startsWith("@color/")) listAdapter.add(ref);
                if (ref.matches("\\?[a-zA-Z0-9]")) listAdapter.add(ref);
            }
        } else if (valueType.equals("@array")) {
            for (Map.Entry entry : mr.valuesArrays.entrySet())
                listAdapter.add("@array/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:array/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@array/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@array/")) listAdapter.add(ref);
        } else if (valueType.equals("@bool")) {
            for (Map.Entry entry : mr.valuesBools.entrySet())
                listAdapter.add("@bool/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:bool/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@bool/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@bool/")) listAdapter.add(ref);
        } else if (valueType.equals("@dimen")) {
            for (Map.Entry entry : mr.valuesDimens.entrySet())
                listAdapter.add("@dimen/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:dimen/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@dimen/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@dimen/")) listAdapter.add(ref);
        } else if (valueType.equals("@integer")) {
            for (Map.Entry entry : mr.valuesIntegers.entrySet())
                listAdapter.add("@integer/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:integer/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@integer/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@integer/")) listAdapter.add(ref);
        } else if (valueType.equals("@menu")) {
            for (Map.Entry entry : mr.menus.entrySet())
                listAdapter.add("@menu/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:menu/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@menu/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@menu/")) listAdapter.add(ref);
        }

        if (valueType.equals("@drawable") || valueType.contains("drawable")) {
            if (valueType.equals("@drawable")) listAdapter.clear();
            for (Map.Entry entry : mr.drawables.entrySet())
                listAdapter.add("@drawable/" + entry.getKey().toString());
            for (Map.Entry entry : mr.mipmaps.entrySet())
                listAdapter.add("@mipmap/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:drawable/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@drawable/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@drawable/")) listAdapter.add(ref);
        } else if (valueType.equals("@layout")) {
            for (Map.Entry entry : mr.layouts.entrySet())
                listAdapter.add("@layout/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:layout/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@layout/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@layout/")) listAdapter.add(ref);
        } else if (valueType.startsWith("@raw")) {
            for (Map.Entry entry : mr.raws.entrySet())
                listAdapter.add("@raw/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:raw/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@raw/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@raw/")) listAdapter.add(ref);
        } else if (valueType.equals("@style")) {
            for (Map.Entry entry : mr.valuesStyles.entrySet())
                listAdapter.add("@style/" + entry.getKey().toString());
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.startsWith("@android:style/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.startsWith("@style/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.startsWith("@style/")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroid)
                if (ref.matches("\\?[a-zA-Z0-9]")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefAndroidX)
                if (ref.matches("\\?[a-zA-Z0-9]")) listAdapter.add(ref);
            for (String ref : AttrValuesRefBase.listRefMaterial3)
                if (ref.matches("\\?[a-zA-Z0-9]")) listAdapter.add(ref);
        }
        Collections.sort(listAdapter);
        return listAdapter;
    }
}
