package jkas.androidpe.resourcesUtils.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import com.caverock.androidsvg.SVGImageView;
import com.google.android.material.card.MaterialCardView;
import java.lang.reflect.Field;
import jkas.androidpe.resourcesUtils.dataInitializer.DataRefManager;
import jkas.androidpe.resourcesUtils.modules.ModuleRes;
import jkas.androidpe.resourcesUtils.requests.AndroidxClassesRequested;
import jkas.androidpe.resourcesUtils.requests.MaterialClassesRequested;
import jkas.androidpe.resourcesUtils.requests.ProjectDataRequested;
import jkas.codeUtil.CodeUtil;
import jkas.codeUtil.Files;
import jkas.codeUtil.Images;
import jkas.codeUtil.XmlManager;
import org.w3c.dom.Element;

/**
 * This class aims to facilitate the recovery of a value to the extent that you want to retrieve a
 * value whose reference is a resource, you just need to pass the reference to the corresponding
 * method as a parameter and it will return the targeted value to you
 *
 * @author JKas
 */
public class ResourcesValuesFixer {
    public static String getString(Context C, final String ref) {
        if (ref == null) return "";
        final String name = parseReferName(ref);
        String value = name;
        if (ref.startsWith("@android:string/")) {
            int id = CodeUtil.getSystemResourceId(android.R.string.class, name);
            if (id != -1) return C.getString(id);
        } else if (ref.startsWith("@string/")) {
            value = DataRefManager.getInstance().currentModuleRes.valuesStrings.get(name.intern());
            if (value == null) {
                int id = CodeUtil.getSystemResourceId(MaterialR.getStringClass(), name);
                if (id != -1) return C.getString(id);
                return name;
            }
            if (value.startsWith("@android:string/") || value.startsWith("@string/"))
                value = getString(C, value);
        }
        return value;
    }

    public static int getColor(Context C, String reference) {
        if (reference == null) return Color.TRANSPARENT;
        try {
            if (CodeUtil.isColor(reference)) return Color.parseColor(reference);
        } catch (Exception err) {
            return Color.TRANSPARENT;
        }
        String name = parseReferName(reference);
        if (reference.startsWith("@android:color/")) {
            int id = CodeUtil.getSystemResourceId(android.R.color.class, name);
            if (id != -1) return C.getColor(id);
        } else if (reference.startsWith("@color/")) {
            if (DataRefManager.getInstance()
                    .currentModuleRes
                    .valuesColors
                    .containsKey(name.intern())) {
                String value =
                        DataRefManager.getInstance()
                                .currentModuleRes
                                .valuesColors
                                .get(name.intern());
                if (value.startsWith("@android:color/") || value.startsWith("@color/"))
                    return getColor(C, value);
                if (CodeUtil.isColor(value)) return Color.parseColor(value);
            }
        } else if (reference.startsWith("?")) {
            int id = getAttr(reference);
            if (id != -1) return ResCodeUtils.getColorFromResolveAttribute(C, id);
        }
        return Color.TRANSPARENT;
    }

    public static String getValuesAsString(Context C, String reference) {
        String name = parseReferName(reference);
        String value = name;

        // string
        if (reference.startsWith("@string") || reference.startsWith("@android:string")) {
            if (reference == null) return "";
            if (reference.startsWith("@android:string/")) {
                int id = CodeUtil.getSystemResourceId(android.R.string.class, name);
                if (id != -1) return C.getString(id);
            } else if (reference.startsWith("@string/")) {
                value =
                        DataRefManager.getInstance()
                                .currentModuleRes
                                .valuesStrings
                                .get(name.intern());
                if (value == null) {
                    int id = CodeUtil.getSystemResourceId(MaterialR.getStringClass(), name);
                    if (id != -1) return C.getString(id);
                    return name;
                }
                return value;
            }
        } else
        // color
        if (reference.startsWith("@color") || reference.startsWith("@android:color")) {
            if (CodeUtil.isColor(reference)) return reference;
            if (reference.startsWith("@android:color/")) {
                int id = CodeUtil.getSystemResourceId(android.R.color.class, name);
                if (id != -1) return "#" + Integer.toHexString(C.getColor(id));
            } else if (reference.startsWith("@color/")) {
                if (DataRefManager.getInstance()
                        .currentModuleRes
                        .valuesColors
                        .containsKey(name.intern())) {
                    value =
                            DataRefManager.getInstance()
                                    .currentModuleRes
                                    .valuesColors
                                    .get(name.intern());
                    return value;
                }
            } else if (reference.startsWith("?")) {
                int id = getAttr(reference);
                return "" + id;
            }
        } else
        // boolean
        if (reference.startsWith("@bool") || reference.startsWith("@android:bool")) {
            if (CodeUtil.isColor(reference)) return reference;
            name = parseReferName(reference);
            if (reference.startsWith("@android:bool/")) {
                int id = CodeUtil.getSystemResourceId(android.R.bool.class, name);
                if (id != -1) return String.valueOf(C.getResources().getBoolean(id));
            } else if (reference.startsWith("@bool/")) {
                if (DataRefManager.getInstance()
                        .currentModuleRes
                        .valuesBools
                        .containsKey(name.intern())) {
                    value =
                            DataRefManager.getInstance()
                                    .currentModuleRes
                                    .valuesBools
                                    .get(name.intern());
                    return value;
                }
            }
        } else
        // dimen
        if (reference.startsWith("@dimen") || reference.startsWith("@android:dimen")) {
            if (CodeUtil.isColor(reference)) return reference;
            name = parseReferName(reference);
            if (reference.startsWith("@android:dimen/")) {
                int id = CodeUtil.getSystemResourceId(android.R.dimen.class, name);
                if (id != -1) return String.valueOf(C.getResources().getDimension(id));
            } else if (reference.startsWith("@dimen/")) {
                if (DataRefManager.getInstance()
                        .currentModuleRes
                        .valuesDimens
                        .containsKey(name.intern())) {
                    value =
                            DataRefManager.getInstance()
                                    .currentModuleRes
                                    .valuesDimens
                                    .get(name.intern());
                    return value;
                }
            }
        } else
        // integer
        if (reference.startsWith("@integer") || reference.startsWith("@android:integer")) {
            if (CodeUtil.isColor(reference)) return reference;
            name = parseReferName(reference);
            if (reference.startsWith("@android:integer/")) {
                int id = CodeUtil.getSystemResourceId(android.R.integer.class, name);
                if (id != -1) return String.valueOf(C.getResources().getInteger(id));
            } else if (reference.startsWith("@integer/")) {
                if (DataRefManager.getInstance()
                        .currentModuleRes
                        .valuesIntegers
                        .containsKey(name.intern())) {
                    value =
                            DataRefManager.getInstance()
                                    .currentModuleRes
                                    .valuesIntegers
                                    .get(name.intern());
                    return value;
                }
            }
        } else
        // attr
        {
            int id = getAttr(reference);
            if (id != -1) {
                int color = getColor(C, reference);
                if (color != Color.TRANSPARENT && color != -1) {
                    return "#" + Integer.toHexString(color);
                }
            }
        }

        return reference;
    }

    public static float getDimen(Context C, String reference) {
        if (reference == null) return -1;
        String name = parseReferName(reference);
        String value = name;

        if (reference.startsWith("@android:dimen/")) {
            int id = CodeUtil.getSystemResourceId(android.R.dimen.class, name);
            if (id != -1) return C.getResources().getDimension(id);
            return -1;
        } else if (reference.startsWith("@dimen/")) {
            if (DataRefManager.getInstance()
                    .currentModuleRes
                    .valuesDimens
                    .containsKey(name.intern())) {
                value =
                        DataRefManager.getInstance()
                                .currentModuleRes
                                .valuesDimens
                                .get(name.intern());
                if (value.contains("/")) return getDimen(C, value);
            }
        } else if (reference.startsWith("?")) {
            int id = getAttr(reference);
            if (id != -1)
                return CodeUtil.getDimenValue(
                        C, ResCodeUtils.getDimenFromResolveAttribute(C, id) + "dp");
        }

        if (!CodeUtil.isDimenValue(value)) return -1;
        return CodeUtil.getDimenValue(C, value) * 0.9f;
    }

    public static Drawable getDrawable(Context C, String reference) {
        ModuleRes currentMR = DataRefManager.getInstance().currentModuleRes;
        if (CodeUtil.isColor(reference)) {
            ColorDrawable cd = new ColorDrawable();
            cd.setColor(Color.parseColor(reference));
            return cd;
        }
        String name = parseReferName(reference);
        if (reference.startsWith("@android:drawable/")) {
            int id = CodeUtil.getSystemResourceId(android.R.drawable.class, name);
            if (id != -1) return C.getDrawable(id);
        } else if (reference.startsWith("@drawable/")) {
            if (currentMR.drawables.containsKey(name.intern())) {
                String path = currentMR.drawables.get(name.intern());
                if (path.endsWith(".xml")) return ImageXmlUtil.createDrawableFromPath(C, path);
                return DrawableWrapper.createFromPath(currentMR.drawables.get(name.intern()));
            }
        } else if (reference.startsWith("@mipmap/")) {
            if (currentMR.mipmaps.containsKey(name.intern())) {
                String path = currentMR.mipmaps.get(name.intern());
                if (path.endsWith(".xml")) return ImageXmlUtil.createDrawableFromPath(C, path);
                return DrawableWrapper.createFromPath(currentMR.mipmaps.get(name.intern()));
            }
        } else if (reference.startsWith("@android:color/")) {
            int id = CodeUtil.getSystemResourceId(android.R.color.class, name);
            if (id != -1) return new ColorDrawable(C.getColor(id));
        } else if (reference.startsWith("@color/")) {
            if (currentMR.valuesColors.containsKey(name.intern()))
                return new ColorDrawable(
                        Color.parseColor(currentMR.valuesColors.get(name.intern())));
        } else if (reference.startsWith("?android:attr/")) {
            int i = getAttr(reference);
            if (i == -1) return null;
            TypedArray a = C.obtainStyledAttributes(new int[] {i});
            Drawable d = a.getDrawable(a.getIndex(0));
            a.recycle();
            return d;
        }
        return null;
    }

    public static int getStyle(String attr) {
        int id = -1;
        try {
            String name = parseReferName(attr).replace(".", "_").replace("*", ".*");
            for (Field field : android.R.style.class.getFields()) {
                String fName = field.getName();
                if (fName.matches(name)) {
                    id = CodeUtil.getSystemResourceId(android.R.style.class, fName);
                    break;
                }
            }

            for (Field field : AndroidX.getStyleClass().getFields()) {
                String fName = field.getName();
                if (fName.matches(name)) {
                    id = CodeUtil.getSystemResourceId(AndroidX.getStyleClass(), fName);
                    break;
                }
            }

            for (Field field : MaterialR.getStyleClass().getFields()) {
                String fName = field.getName();
                if (fName.matches(name)) {
                    id = CodeUtil.getSystemResourceId(MaterialR.getStyleClass(), fName);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getAttr(String attr) {
        try {
            String name;
            if (attr.startsWith("?android:attr/")) {
                name = parseReferName(attr);
            } else if (attr.startsWith("?android:")) {
                name = parseReferName(attr, ":");
            } else if (attr.startsWith("@android:attr/")) {
                name = parseReferName(attr);
            } else if (attr.startsWith("?attr/android:")) {
                name = parseReferName(attr, ":");
            } else if (attr.startsWith("?attr/")) {
                name = parseReferName(attr, "/");
            } else if (attr.startsWith("?")) {
                name = attr.substring(1);
            } else {
                return -1;
            }

            int id = CodeUtil.getSystemResourceId(android.R.attr.class, name);
            if (id == -1) id = CodeUtil.getSystemResourceId(AndroidX.getAttrClass(), name);
            if (id == -1) id = CodeUtil.getSystemResourceId(MaterialR.getAttrClass(), name);
            if (id != -1) return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String parseReferName(final String reference) {
        return parseReferName(reference, "/");
    }

    public static String parseReferName(final String reference, String sep) {
        return reference.substring(reference.indexOf(sep) + 1);
    }

    public static class ImageXmlUtil {
        public static Drawable createDrawableFromPath(Context C, String path) {
            String p = null;
            if (path.toLowerCase().endsWith(".svg")) p = path;
            else p = convertXmlFileToSvg(C, path);
            SVGImageView img = new SVGImageView(C);
            Images.setImageFromDirSvgFile(p, img);
            return img.getDrawable();
        }

        private static String convertXmlFileToSvg(Context C, String path) {
            XmlManager svgFile = createSvgFile(C);
            XmlManager xmlFile = new XmlManager(C);
            if (!xmlFile.initializeFromPath(path)) return null;
            for (Element element : xmlFile.getElementsByTagName("path")) {
                Element pathTag = svgFile.getDocument().createElement("path");
                pathTag.setAttribute("d", element.getAttribute("android:pathData"));
                int color = getColor(C, element.getAttribute("android:fillColor"));
                String colorHex = Integer.toHexString(color);
                if (colorHex.length() > 6) colorHex = colorHex.substring(2);
                pathTag.setAttribute("fill", "#" + colorHex);
                for (Element e : svgFile.getElementsByTagName("svg")) e.appendChild(pathTag);
            }
            svgFile.saveAllModif();
            return svgFile.getPath();
        }

        private static XmlManager createSvgFile(Context C) {
            String path = ProjectDataRequested.getInstance().listener.onTmpPathNeeded();
            path += "/svgImg/image.svg";
            Files.writeFile(path, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<svg/>");

            XmlManager file = new XmlManager(C);
            file.initializeFromPath(path);

            for (Element svgTag : file.getElementsByTagName("svg")) {
                svgTag.setAttribute("xmlns", "http://www.w3.org/2000/svg");
                svgTag.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
                svgTag.setAttribute("version", "1.1");
                svgTag.setAttribute("id", "mdi-ab-testing");
                svgTag.setAttribute("width", "24");
                svgTag.setAttribute("height", "24");
                svgTag.setAttribute("viewBox", "0 0 24 24");
            }
            file.saveAllModif();
            return file;
        }
    }

    public static class AndroidX {
        public static Class getAnimClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXAnimClassNeeded();
        }

        public static Class getAttrClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXAttrClassNeeded();
        }

        public static Class getBoolClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXBoolClassNeeded();
        }

        public static Class getColorClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXColorClassNeeded();
        }

        public static Class getDimenClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXDimenClassNeeded();
        }

        public static Class getDrawableClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXDrawableClassNeeded();
        }

        public static Class getIntegerClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXIntegerClassNeeded();
        }

        public static Class getLayoutClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXLayoutClassNeeded();
        }

        public static Class getStringClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXStringClassNeeded();
        }

        public static Class getStyleClass() {
            return AndroidxClassesRequested.getInstance().listener.onAndroidXStyleClassNeeded();
        }
    }

    public static class MaterialR {
        public static Class getAnimClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialAnimClassNeeded();
        }

        public static Class getAttrClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialAttrClassNeeded();
        }

        public static Class getBoolClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialBoolClassNeeded();
        }

        public static Class getColorClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialColorClassNeeded();
        }

        public static Class getDimenClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialDimenClassNeeded();
        }

        public static Class getDrawableClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialDrawableClassNeeded();
        }

        public static Class getIntegerClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialIntegerClassNeeded();
        }

        public static Class getLayoutClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialLayoutClassNeeded();
        }

        public static Class getStringClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialStringClassNeeded();
        }

        public static Class getStyleClass() {
            return MaterialClassesRequested.getInstance().listener.onMaterialStyleClassNeeded();
        }
    }
}
