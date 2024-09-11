package jkas.androidpe.resourcesUtils.attrs.layout;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static jkas.androidpe.resourcesUtils.attrs.AllAttrBase.VALUES_GRAVITY;
import static jkas.androidpe.resourcesUtils.attrs.AllAttrBase.VALUES_BOOLEAN;
import static jkas.androidpe.resourcesUtils.attrs.AllAttrBase.VALUES_TINT_MODE;

/**
 * @author JKas
 */
public class AttrViews {
    public static LinkedHashMap<String, String[]> baseAttrs = new LinkedHashMap<>();
    public static LinkedHashMap<String, String[]> baseAttrsApp = new LinkedHashMap<>();
    public static LinkedHashMap<String, String[]> allAttrs = new LinkedHashMap<>();

    public static ArrayList<String> usedByAssist = new ArrayList<>();

    static {
        initUsed();
        initBaseAttrs();
        initBaseAttrsApp();
        initAllAttrs();
    }

    private static void initUsed() {
        usedByAssist.clear();
        usedByAssist.add("layout_weight");
        usedByAssist.add("layout_width");
        usedByAssist.add("layout_height");
        usedByAssist.add("id");
        usedByAssist.add("padding");
        usedByAssist.add("paddingStart");
        usedByAssist.add("paddingEnd");
        usedByAssist.add("paddingLeft");
        usedByAssist.add("paddingTop");
        usedByAssist.add("paddingRight");
        usedByAssist.add("paddingBottom");
        usedByAssist.add("layout_margin");
        usedByAssist.add("layout_marginStart");
        usedByAssist.add("layout_marginEnd");
        usedByAssist.add("layout_marginLeft");
        usedByAssist.add("layout_marginTop");
        usedByAssist.add("layout_marginRight");
        usedByAssist.add("layout_marginBottom");
        usedByAssist.add("visibility");
        usedByAssist.add("backgroundTint");
        usedByAssist.add("background");
        usedByAssist.add("gravity");
        usedByAssist.add("layout_gravity");
        usedByAssist.add("alpha");
    }

    private static void initBaseAttrs() {
        baseAttrs.clear();
        baseAttrs.put("style", new String[] {"@style"});
        baseAttrs.put("foreground", new String[] {"@drawable"});
        baseAttrs.put("foregroundTint", new String[] {"@color"});
        baseAttrs.put("foregroundTintMode", VALUES_TINT_MODE);
        baseAttrs.put("backgroundTintMode", VALUES_TINT_MODE);
        baseAttrs.put("clipToOutline", VALUES_BOOLEAN);
        baseAttrs.put("contentDescription", new String[] {"@string"});
        baseAttrs.put("contextClickable", VALUES_BOOLEAN);
        baseAttrs.put("defaultFocusHightlightEnabled", VALUES_BOOLEAN);
        baseAttrs.put("drawingCacheQuality", new String[] {"auto", "low", "medium", "high"});
        baseAttrs.put("duplicateParentState", VALUES_BOOLEAN);
        baseAttrs.put("elevation", new String[] {"@dimen"});
        baseAttrs.put("fadeScrollbars", VALUES_BOOLEAN);
        baseAttrs.put("fadingEdgeLength", new String[] {"@dimen"});
        baseAttrs.put("filterTouchesWhenObscured", VALUES_BOOLEAN);
        baseAttrs.put("foreground", new String[] {"@drawable"});
        baseAttrs.put("foregroundGravity", VALUES_GRAVITY);
        baseAttrs.put("foregroundTint", new String[] {"@color"});
        baseAttrs.put("foregroundTintMode", VALUES_TINT_MODE);
        baseAttrs.put("minHeight", new String[] {"@dimen"});
        baseAttrs.put("minWidth", new String[] {"@dimen"});
        baseAttrs.put("pivotX", null);
        baseAttrs.put("pivotY", null);
        baseAttrs.put("rotation", null);
        baseAttrs.put("rotationX", null);
        baseAttrs.put("rotationY", null);
        baseAttrs.put("scaleX", null);
        baseAttrs.put("scaleY", null);
        baseAttrs.put("textAlignment", VALUES_BOOLEAN);
        baseAttrs.put("textDirection", new String[] {"@string"});
        baseAttrs.put("toolipText", new String[] {"@string"});
        baseAttrs.put("transformPivotX", null);
        baseAttrs.put("transformPivotY", null);
        baseAttrs.put("translationX", null);
        baseAttrs.put("translationY", null);
        baseAttrs.put("translationZ", null);
    }

    private static void initBaseAttrsApp() {
        baseAttrsApp.clear();
    }

    private static void initAllAttrs() {
        allAttrs.put("icon", new String[] {"@drawable"});
        allAttrs.put("layout_anchor", new String[] {"@id"});
        allAttrs.put("layout_anchorGravity", VALUES_GRAVITY);
        allAttrs.put("foregroundGravity", VALUES_GRAVITY);
        allAttrs.put("measureAllChildren", VALUES_BOOLEAN);
        allAttrs.put("alignmentMode", new String[] {"alignBounds", "alignMargins"});
        allAttrs.put("columnCount", null);
        allAttrs.put("columnOrderPreserved", VALUES_BOOLEAN);
        allAttrs.put("orientation", new String[] {"vertical", "horizontal"});
        allAttrs.put("rowCount", null);
        allAttrs.put("rowOrderPreserved", VALUES_BOOLEAN);
        allAttrs.put("useDefaultMargins", VALUES_BOOLEAN);
        allAttrs.put("fillViewport", VALUES_BOOLEAN);
        allAttrs.put("baselineAligned", VALUES_BOOLEAN);
        allAttrs.put("baselineAlignedChildIndex", new String[] {"@id"});
        allAttrs.put("divider", new String[] {"@drawable"});
        allAttrs.put("gravity", VALUES_GRAVITY);
        allAttrs.put("measureWithLargestChild", VALUES_BOOLEAN);
        allAttrs.put("weightSum", null);
        allAttrs.put("checkedButton", new String[] {"@id"});
        allAttrs.put(
                "ignoreGravity",
                new String[] {
                    "above",
                    "align_baseline",
                    "align_bottom",
                    "align_end",
                    "align_left",
                    "align_parent_bottom",
                    "align_parent_end",
                    "align_parent_left",
                    "align_parent_right",
                    "align_parent_start",
                    "align_parent_top",
                    "align_right",
                    "align_start",
                    "align_top",
                    "align_below",
                    "center_horizontal",
                    "center_vertical",
                    "center_in_parent",
                    "end_of",
                    "left_of",
                    "right_of",
                    "start_of",
                    "true"
                });
        allAttrs.put("autoStart", VALUES_BOOLEAN);
        allAttrs.put("flipInterval", null); // not yet
        allAttrs.put("fabAlignmentMode", new String[] {"center", "end"});
        allAttrs.put("fabAnchorMode", new String[] {"cradle", "embed"});
        allAttrs.put("fabAnimationMode", new String[] {"scale", "slide"});
        allAttrs.put("fabCradleMargin", new String[] {"@dimen"});
        allAttrs.put("fabCradleRoundedCornerRadius", new String[] {"@dimen"});
        allAttrs.put("fabCradleVerticalOffset", new String[] {"@dimen"});
        allAttrs.put("hideOnScroll", null); // not yet
        allAttrs.put("paddingBottomSystemWindowInsets", VALUES_BOOLEAN);
        allAttrs.put("menu", new String[] {"@menu"});
        allAttrs.put("menuAlignmentMode", new String[] {"auto", "start"});
        allAttrs.put("navigationIconTint", new String[] {"@color"});
        allAttrs.put("fabAlignmentModeEndMargin", new String[] {"@dimen"}); // not yet
        allAttrs.put("itemHorizontalTranslationEnabled", VALUES_BOOLEAN);
        allAttrs.put(
                "layout_insetEdge",
                new String[] {"left", "top", "right", "bottom", "start", "end", "none"});
        allAttrs.put("layout_behavior", new String[] {"@string"});
        allAttrs.put("indicatorInset", new String[] {"@dimen"});
        allAttrs.put("indicatorSize", new String[] {"@dimen"});
        allAttrs.put("elevation", new String[] {"@dimen"});
        allAttrs.put("ensureMinTouchTargetSize", null);
        allAttrs.put("fabCustomSize", new String[] {"@dimen"});
        allAttrs.put("fabSize", new String[] {"auto", "mini", "normal"});
        allAttrs.put("hideMotionSpec", null);
        allAttrs.put("hoveredFocusedTranslationZ", null);
        allAttrs.put("maxImageSize", new String[] {"@dimen"});
        allAttrs.put("pressedTranslationZ", null);
        allAttrs.put("rippleColor", new String[] {"@color"});
        allAttrs.put("showMotionSpec", null);
        allAttrs.put("useCompatPadding", VALUES_BOOLEAN);
        allAttrs.put("srcCompat", new String[] {"@drawable"});
        allAttrs.put("borderWidth", new String[] {"@dimen"});
        allAttrs.put("dropDownBackgroundTint", new String[] {"@color"});
        allAttrs.put("simpleItemSelectedColor", new String[] {"@color"});
        allAttrs.put("simpleItemSelectedRippleColor", new String[] {"@color"});
        allAttrs.put("popupElevation", new String[] {"@dimen"});
        allAttrs.put("popupBackground", new String[] {"@color"});
        allAttrs.put("selectionRequired", VALUES_BOOLEAN);
        allAttrs.put("singleSelection", VALUES_BOOLEAN);
        allAttrs.put("android_checkable", VALUES_BOOLEAN);
        allAttrs.put("cardForegroundColor", new String[] {"@color"});
        allAttrs.put("cardElevation", new String[] {"@dimen"});
        allAttrs.put("checkedIcon", VALUES_BOOLEAN);
        allAttrs.put("checkedIconMargin", new String[] {"@dimen"});
        allAttrs.put("checkedIconSize", null);
        allAttrs.put("checkedIconTint", new String[] {"@color"});
        allAttrs.put("checked", VALUES_BOOLEAN);
        allAttrs.put("buttonIcon", new String[] {"@drawable"});
        allAttrs.put("buttonIconTint", new String[] {"@color"});
        allAttrs.put("buttonIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("errorShown", VALUES_BOOLEAN);
        allAttrs.put("buttonIconTint", new String[] {"@color"});
        allAttrs.put("buttonIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("errorShown", VALUES_BOOLEAN);
        allAttrs.put("dividerColor", new String[] {"@color"});
        allAttrs.put("dividerInsetEnd", null);
        allAttrs.put("dividerInsetStart", null);
        allAttrs.put("dividerThickness", null);
        allAttrs.put("buttonIconTint", new String[] {"@color"});
        allAttrs.put("buttonIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("thumbIcon", new String[] {"@drawable"});
        allAttrs.put("thumbIconSize", new String[] {"@dimen"});
        allAttrs.put("thumbIconTint", new String[] {"@color"});
        allAttrs.put("thumbIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("trackDecoration", null);
        allAttrs.put("trackDecorationTint", new String[] {"color"});
        allAttrs.put("trackDecorationTintMode", VALUES_TINT_MODE);
        allAttrs.put("title", new String[] {"@string"});
        allAttrs.put("subTitle", new String[] {"@string"});
        allAttrs.put("strokeColor", new String[] {"@color"});
        allAttrs.put("strokeWidth", new String[] {"@dimen"});
        allAttrs.put("itemMinHeight", new String[] {"@dimen"});
        allAttrs.put("menuGravity", null);
        allAttrs.put("itemBackground", new String[] {"@color"});
        allAttrs.put("itemHorizontalPadding", new String[] {"@dimen"});
        allAttrs.put("itemIconPadding", new String[] {"@dimen"});
        allAttrs.put("itemIconSize", new String[] {"@dimen"});
        allAttrs.put("itemIconTint", new String[] {"@color"});
        allAttrs.put("itemIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("itemMaxLines", null);
        allAttrs.put("itemTextAppearance", new String[] {"@style"});
        allAttrs.put("itemTextAppearanceActiveBoldEnabled", VALUES_BOOLEAN);
        allAttrs.put("itemTextColor", new String[] {"@color"});
        allAttrs.put("itemVerticalPadding", new String[] {"@dimen"});
        allAttrs.put("minSeparation", null);
        allAttrs.put("stepSize", null);
        allAttrs.put("values", null);
        allAttrs.put("valueFrom", null);
        allAttrs.put("valueTo", null);
        allAttrs.put("haloRadius", null);
        allAttrs.put("haloColor", new String[] {"@string"});
        allAttrs.put("labelBehavior", null);
        allAttrs.put("thumbColor", new String[] {"@color"});
        allAttrs.put("thumbElevation", new String[] {"@dimen"});
        allAttrs.put("thumbHeight", null);
        allAttrs.put("thumbStrokeColor", new String[] {"@color"});
        allAttrs.put("thumbStrokeWidth", new String[] {"@dimen"});
        allAttrs.put("thumbTrackGapSize", null);
        allAttrs.put("thumbWidth", null);
        allAttrs.put("tickColor", new String[] {"color"});
        allAttrs.put("tickColorActive", new String[] {"color"});
        allAttrs.put("tickColorInactive", new String[] {"color"});
        allAttrs.put("tickVisible", VALUES_BOOLEAN);
        allAttrs.put("trackColor", new String[] {"color"});
        allAttrs.put("trackColorActive", new String[] {"color"});
        allAttrs.put("trackColorInactive", new String[] {"color"});
        allAttrs.put("value", null);
        allAttrs.put("haloRadius", null);
        allAttrs.put("labelBehavior", null);
        allAttrs.put("tickVisible", VALUES_BOOLEAN);
        allAttrs.put("tabBackground", new String[] {"@drawable"});
        allAttrs.put("tabContentStart", null); // not yet
        allAttrs.put("tabGravity", VALUES_GRAVITY);
        allAttrs.put("tabIndicatorAnimationMode", null); // not yet
        allAttrs.put("tabIndicatorColor", new String[] {"@color"});
        allAttrs.put("tabIndicatorFullWidth", VALUES_BOOLEAN);
        allAttrs.put("tabIndicatorGravity", VALUES_GRAVITY);
        allAttrs.put("tabIndicatorHeight", VALUES_BOOLEAN);
        allAttrs.put("tabInlineLabel", null); // not yet
        allAttrs.put("tabMaxWidth", new String[] {"@dimen"}); // not yet
        allAttrs.put("tabMinWidth", new String[] {"@dimen"}); // not yet
        allAttrs.put("tabMode", new String[] {"fixed", "scrollable"});
        allAttrs.put("tabPadding", new String[] {"@dimen"});
        allAttrs.put("tabPaddingBottom", new String[] {"@dimen"});
        allAttrs.put("tabPaddingEnd", new String[] {"@dimen"});
        allAttrs.put("tabPaddingStart", new String[] {"@dimen"});
        allAttrs.put("tabPaddingTop", new String[] {"@dimen"});
        allAttrs.put("tabRippleColor", new String[] {"@color"});
        allAttrs.put("tabSelectedTextColor", new String[] {"@color"});
        allAttrs.put("tabTextColor", new String[] {"@color"});
        allAttrs.put("hint", new String[] {"@string"});
        allAttrs.put("maxEms", null);
        allAttrs.put("minEms", null);
        allAttrs.put("boxStrokeErrorColor", new String[] {"@color"});
        allAttrs.put("boxStrokeWidth", new String[] {"@dimen"});
        allAttrs.put("counterEnabled", null);
        allAttrs.put("counterMaxLength", null);
        allAttrs.put("counterOverflowTextAppearance", null);
        allAttrs.put("counterOverflowTextColor", new String[] {"@color"});
        allAttrs.put("counterTextColor", new String[] {"@color"});
        allAttrs.put("cursorColor", new String[] {"@color"});
        allAttrs.put("cursorErrorColor", new String[] {"@color"});
        allAttrs.put("endIconCheckable", VALUES_BOOLEAN);
        allAttrs.put("endIconContentDescription", new String[] {"@string"});
        allAttrs.put("endIconDrawable", null);
        allAttrs.put("endIconMode", null);
        allAttrs.put("endIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("errorContentDescription", new String[] {"@string"});
        allAttrs.put("errorEnabled", VALUES_BOOLEAN);
        allAttrs.put("errorIconDrawable", new String[] {"@drawable"});
        allAttrs.put("errorIconTint", new String[] {"@color"});
        allAttrs.put("errorIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("helperTextEnabled", VALUES_BOOLEAN);
        allAttrs.put("hintEnabled", VALUES_BOOLEAN);
        allAttrs.put("hintTextColor", new String[] {"@color"});
        allAttrs.put("passwordToggleContentDescription", null);
        allAttrs.put("passwordToggleDrawable", new String[] {"@drawable"});
        allAttrs.put("passwordToggleEnabled", VALUES_BOOLEAN);
        allAttrs.put("passwordToggleTint", new String[] {"@color"});
        allAttrs.put("passwordToggleTintMode", VALUES_TINT_MODE);
        allAttrs.put("placeholderTextColor", new String[] {"@color"});
        allAttrs.put("prefixTextColor", new String[] {"@color"});
        allAttrs.put("startIconCheckable", VALUES_BOOLEAN);
        allAttrs.put("startIconContentDescription", null);
        allAttrs.put("startIconDrawable", null);
        allAttrs.put("startIconTint", new String[] {"@color"});
        allAttrs.put("startIconTintMode", VALUES_TINT_MODE);
        allAttrs.put("suffixTextColor", new String[] {"@color"});
        allAttrs.put("textOn", new String[] {"@string"});
        allAttrs.put("textOff", new String[] {"@string"});
        allAttrs.put("disabledAlpha", null);
        allAttrs.put("dial", null);
        allAttrs.put("dialTint", new String[] {"@color"});
        allAttrs.put("dialTintMode", VALUES_TINT_MODE);
        allAttrs.put("hand_hour", null);
        allAttrs.put("hand_hourTint", new String[] {"@color"});
        allAttrs.put("hand_hourTintMode", VALUES_TINT_MODE);
        allAttrs.put("hand_minute", null);
        allAttrs.put("hand_minuteTint", new String[] {"@color"});
        allAttrs.put("hand_minuteTintMode", VALUES_TINT_MODE);
        allAttrs.put("firstDayOfWeek", null);
        allAttrs.put("hand_second", null);
        allAttrs.put("hand_secondTint", new String[] {"@style"});
        allAttrs.put("hand_secondTintMode", VALUES_TINT_MODE);
        allAttrs.put("timeZone", new String[] {"@string"});
        allAttrs.put("dropDownHeight", new String[] {"@dimen"});
        allAttrs.put("dropDownWidth", new String[] {"@dimen"});
        allAttrs.put("dropDownSelector", new String[] {"@color"});
        allAttrs.put("dropDownVerticalOffset", new String[] {"@dimen"});
        allAttrs.put("dropDownHorizontalOffset", new String[] {"@dimen"});
        allAttrs.put("popupBackground", new String[] {"@color"});
        allAttrs.put("dateTextAppearance", new String[] {"@style"});
        allAttrs.put("firstDayOfWeek", null);
        allAttrs.put("maxDate", null);
        allAttrs.put("minDate", null);
        allAttrs.put("countDown", VALUES_BOOLEAN);
        allAttrs.put("format", null);
        allAttrs.put("columnWidth", new String[] {"@dimen"});
        allAttrs.put("numColumns", null);
        allAttrs.put(
                "stretchMode",
                new String[] {"colulmWidth", "none", "spacingWidth", "spacingWidthUniform"});
        allAttrs.put("verticalSpacing", new String[] {"@dimen"});
        allAttrs.put("adjustViewBounds", VALUES_BOOLEAN);
        allAttrs.put("baseline", new String[] {"@dimen"});
        allAttrs.put("baselineAlignBottom", VALUES_BOOLEAN);
        allAttrs.put("cropToPadding", VALUES_BOOLEAN);
        allAttrs.put(
                "scaleType",
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
        allAttrs.put("src", new String[] {"@drawable"});
        allAttrs.put("tint", new String[] {"@color"});
        allAttrs.put(
                "hintMode",
                new String[] {"add", "multiply", "screen", "src_atop", "src_in", "src_over"});
        allAttrs.put("divider", null);
        allAttrs.put("dividerHeight", new String[] {"@dimen"});
        allAttrs.put("footerDividersEnable", VALUES_BOOLEAN);
        allAttrs.put("headerDividersEnabled", VALUES_BOOLEAN);
        allAttrs.put("indeterminate", VALUES_BOOLEAN);
        allAttrs.put("indeterminateBehavior", new String[] {"cycle", "repeat"});
        allAttrs.put("indeterminateDrawable", new String[] {"@drawable"});
        allAttrs.put("indeterminateDuration", null);
        allAttrs.put("indeterminateOnly", VALUES_BOOLEAN);
        allAttrs.put("indeterminateTint", new String[] {"@color"});
        allAttrs.put("indeterminateTintMode", VALUES_TINT_MODE);
        allAttrs.put("max", null);
        allAttrs.put("min", null);
        allAttrs.put("mirrorForRtl", VALUES_BOOLEAN);
        allAttrs.put("progress", null);
        allAttrs.put("progressBackgroundTint", new String[] {"@color"});
        allAttrs.put("progressBackgroundTintMode", VALUES_TINT_MODE);
        allAttrs.put("progressTint", new String[] {"@color"});
        allAttrs.put("progressTintMode", VALUES_TINT_MODE);
        allAttrs.put("econdaryProgress", null);
        allAttrs.put("secondaryProgressTint", new String[] {"@color"});
        allAttrs.put("secondaryProgressTintMode", VALUES_TINT_MODE);
        allAttrs.put("isIndicator", VALUES_BOOLEAN);
        allAttrs.put("numStars", null);
        allAttrs.put("rating", null);
        allAttrs.put("dropDownHeight", new String[] {"@dimen"});
        allAttrs.put("dropDownWidth", new String[] {"@dimen"});
        allAttrs.put("dropDownSelector", new String[] {"@color"});
        allAttrs.put("dropDownVerticalOffset", new String[] {"@dimen"});
        allAttrs.put("dropDownHorizontalOffset", new String[] {"@dimen"});
        allAttrs.put("spinnerMode", new String[] {"dialog", "dropdown"});
        allAttrs.put("showText", VALUES_BOOLEAN);
        allAttrs.put("textOn", new String[] {"@string"});
        allAttrs.put("textOff", new String[] {"@string"});
        allAttrs.put("format12Hour", null);
        allAttrs.put("format24Hour", null);
        allAttrs.put("timeZone", null);
        allAttrs.put("ems", null);
        allAttrs.put("minEms", null);
        allAttrs.put("enabled", VALUES_BOOLEAN);
        allAttrs.put("width", new String[] {"@dimen"});
        allAttrs.put("height", new String[] {"@dimen"});
        allAttrs.put("maxLength", null);
        allAttrs.put("maxLines", null);
        allAttrs.put("minLines", null);
        allAttrs.put("hint", new String[] {"@string"});
        allAttrs.put(
                "inputType",
                new String[] {
                    "none",
                    "text",
                    "textCapCharacters",
                    "textCapWords",
                    "textCapSentences",
                    "textAutoCorrect",
                    "textAutoComplete",
                    "textMultiLine",
                    "textImeMultiLine",
                    "textNoSuggestions",
                    "textUri",
                    "textEmailAddress",
                    "textEmailSubject",
                    "textShortMessage",
                    "textLongMessage",
                    "textPersonName",
                    "textPostalAddress",
                    "textPassword",
                    "textVisiblePassword",
                    "textWebEditText",
                    "textFilter",
                    "textPhonetic",
                    "textWebEmailAddress",
                    "textWebPassword",
                    "number",
                    "numberSigned",
                    "numberDecimal",
                    "numberPassword",
                    "phone",
                    "datetime",
                    "date",
                    "time"
                });
        allAttrs.put("lines", null);
        allAttrs.put("maxEms", null);
        allAttrs.put("maxHeight", new String[] {"@dimen"});
        allAttrs.put("maxWidth", new String[] {"@dimen"});
        allAttrs.put("minHeight", new String[] {"@dimen"});
        allAttrs.put("minWidth", new String[] {"@dimen"});
        allAttrs.put("shadowColor", new String[] {"@color"});
        allAttrs.put("shadowDx", null);
        allAttrs.put("shadowDy", null);
        allAttrs.put("shadowRadius", null);
        allAttrs.put("singleLine", VALUES_BOOLEAN);
        allAttrs.put("text", new String[] {"@string"});
        allAttrs.put("textAppearance", new String[] {"@style"});
        allAttrs.put("textColor", new String[] {"@color"});
        allAttrs.put("textColorHighlight", new String[] {"@color"});
        allAttrs.put("textColorHint", new String[] {"@color"});
        allAttrs.put("textColorLink", new String[] {"@color"});
        allAttrs.put("textScaleX", null);
        allAttrs.put("textSize", new String[] {"@dimen"});
        allAttrs.put("textStyle", new String[] {"bold", "italic", "normal"});
        allAttrs.put("typeface", new String[] {"monospace", "normal", "sans", "serif"});
    }
}
