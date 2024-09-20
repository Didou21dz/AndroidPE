package jkas.androidpe.layoutUiDesigner.dialog.attrs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.text.HtmlCompat;
import androidx.core.util.Pair;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jkas.androidpe.layoutUiDesigner.databinding.DialogLayoutConstraintLayoutBinding;
import jkas.androidpe.resources.R;
import jkas.androidpe.resourcesUtils.adapters.AttrViewAdapter;
import jkas.androidpe.resourcesUtils.adapters.AttrViewDataAdapter;
import jkas.androidpe.resourcesUtils.attrs.AllAttrBase;
import jkas.androidpe.resourcesUtils.dialog.DialogAutoComplete;
import jkas.androidpe.resourcesUtils.dialog.DialogBuilder;
import jkas.androidpe.resourcesUtils.utils.ResourcesValuesFixer;
import jkas.androidpe.resourcesUtils.utils.ViewUtils;
import jkas.codeUtil.CodeUtil;
import jkas.codeUtil.XmlManager;
import org.w3c.dom.Element;

/**
 * @author JKas
 */
public class DialogLayoutConstraintLayout {
    private OnChangedListener listener;
    private Context C;
    private Element element;
    private DialogLayoutConstraintLayoutBinding binding;
    private BottomSheetDialog BSD;
    private String[] listId;
    private ArrayList<Pair<String, String[]>> listRelativePosition = new ArrayList<>();
    private ArrayList<Pair<String, String[]>> listMargins = new ArrayList<>();
    private ArrayList<Pair<String, String[]>> listCircular = new ArrayList<>();
    private ArrayList<Pair<String, String[]>> listDimensionMinMax = new ArrayList<>();
    private ArrayList<Pair<String, String[]>> listWeighted = new ArrayList<>();

    public DialogLayoutConstraintLayout(Context c) {
        C = c;
    }

    private void loadData() {
        loadConstraints();
        loadMargins();
        loadRatio();
    }

    private void loadRatio() {
        try {
            String value = element.getAttribute("app:layout_constraintDimensionRatio");
            Pattern pattern = Pattern.compile("\\b(\\d+):(\\d+)\\b");
            Matcher matcher = pattern.matcher(value);
            if (!value.trim().isEmpty()) {
                if (matcher.find()) {
                    String width = matcher.group(1);
                    String height = matcher.group(2);
                    binding.tvRatioWidth.setText(width);
                    binding.tvRatioHeight.setText(height);
                }
            } else {
                binding.tvRatioWidth.setText("1");
                binding.tvRatioHeight.setText("1");
            }
        } catch (Exception err) {
            //
        }
    }

    private void loadBias() {
        String valueV = element.getAttribute("app:layout_constraintVertical_bias");
        String valueH = element.getAttribute("app:layout_constraintHorizontal_bias");
        if (valueV.trim().isEmpty()) binding.rsBiasV.setValues(new Float[] {50.0f});
        if (valueH.trim().isEmpty()) binding.rsBiasH.setValues(new Float[] {50.0f});
        else {
            try {
                binding.rsBiasV.setValues(new Float[] {Float.parseFloat(valueV) * 100});
            } catch (Exception err) {
                binding.rsBiasV.setValues(new Float[] {50.0f});
            }
            try {
                binding.rsBiasH.setValues(new Float[] {Float.parseFloat(valueH) * 100});
            } catch (Exception err) {
                binding.rsBiasH.setValues(new Float[] {50.0f});
            }
        }

        if (element.getAttribute("app:layout_constraintStart_toStartOf").trim().equals("parent")
                && element.getAttribute("app:layout_constraintEnd_toEndOf")
                        .trim()
                        .equals("parent")) {
            binding.rsBiasH.setEnabled(true);
        } else {
            binding.rsBiasH.setValues(new Float[] {50.0f});
            binding.rsBiasH.setEnabled(false);
            element.removeAttribute("app:layout_constraintHorizontal_bias");
        }
        if (!binding.rsBiasH.isEnabled()) {
            if (element.getAttribute("app:layout_constraintLeft_toLeftOf").trim().equals("parent")
                    && element.getAttribute("app:layout_constraintRight_toRightOf")
                            .trim()
                            .equals("parent")) {
                binding.rsBiasH.setEnabled(true);
            } else {
                binding.rsBiasH.setValues(new Float[] {50.0f});
                binding.rsBiasH.setEnabled(false);
                element.removeAttribute("app:layout_constraintHorizontal_bias");
            }
        }

        if (element.getAttribute("app:layout_constraintTop_toTopOf").trim().equals("parent")
                || element.getAttribute("app:layout_constraintBottom_toBottomOf")
                        .trim()
                        .equals("parent")) {
            binding.rsBiasV.setEnabled(true);
        } else {
            binding.rsBiasV.setValues(new Float[] {50.0f});
            binding.rsBiasV.setEnabled(false);
            element.removeAttribute("app:layout_constraintVertical_bias");
        }
    }

    private void loadMargins() {
        loadMargins(binding.btnLeft, "Left");
        loadMargins(binding.btnTop, "Top");
        loadMargins(binding.btnRight, "Right");
        loadMargins(binding.btnBottom, "Bottom");
    }

    private void loadMargins(final MaterialButton btn, String val) {
        String value = element.getAttribute("android:layout_margin" + val).trim();
        if (!value.isEmpty()) {
            if (value.startsWith("@") || value.startsWith("?")) btn.setText("ref");
            else btn.setText(value);
        } else btn.setText("16dp");
    }

    private void loadConstraints() {
        binding.linCurrentConstraints.removeAllViews();
        if (!loadConstraints(
                binding.tvAddConstraintLeft, binding.btnLeft, "Left_toLeftOf", "Left_toRightOf"))
            loadConstraints(
                    binding.tvAddConstraintLeft,
                    binding.btnLeft,
                    "Start_toStartOf",
                    "Start_toEndOf");
        loadConstraints(
                binding.tvAddConstraintTop, binding.btnTop, "Top_toTopOf", "Top_toBottomOf");
        loadConstraints(
                binding.tvAddConstraintRight,
                binding.btnRight,
                "Right_toRightOf",
                "Right_toLeftOf");
        if (!loadConstraints(
                binding.tvAddConstraintBottom,
                binding.btnBottom,
                "Bottom_toBottomOf",
                "Bottom_toTopOf"))
            loadConstraints(
                    binding.tvAddConstraintBottom,
                    binding.btnBottom,
                    "End_toEndOf",
                    "End_toStartOf");
        loadBias();
    }

    private boolean loadConstraints(
            final TextView tvConstraint,
            final MaterialButton btn,
            final String v1,
            final String v2) {
        String vF = "";
        String value = element.getAttribute("app:layout_constraint" + v1).trim();
        vF = v1;
        if (value.isEmpty()) {
            value = element.getAttribute("app:layout_constraint" + v2).trim();
            vF = v2;
        }
        if (!value.isEmpty()) {
            ViewUtils.setBackground(
                    tvConstraint,
                    ResourcesValuesFixer.getColor(C, "?colorOnSurface"),
                    0,
                    Color.TRANSPARENT,
                    43f);
            tvConstraint.setText("");
            btn.setVisibility(View.VISIBLE);

            String s = vF;
            s = s.replace("_", "  >  ");
            s += "  <b>" + value + "</b>";
            TextView tv = new TextView(C);
            tv.setTypeface(Typeface.MONOSPACE);
            tv.setLayoutParams(CodeUtil.getLayoutParamsMW(8));
            tv.setText(HtmlCompat.fromHtml(s, HtmlCompat.FROM_HTML_MODE_COMPACT));
            tv.setTextSize(12f);
            tv.setPadding(8, 8, 8, 8);
            binding.linCurrentConstraints.addView(tv);
            return true;
        } else {
            ViewUtils.setBackground(
                    tvConstraint,
                    ResourcesValuesFixer.getColor(C, "?colorOnTertiary"),
                    0,
                    Color.TRANSPARENT,
                    43f);
            tvConstraint.setText("+");
            btn.setVisibility(View.INVISIBLE);
        }
        return false;
    }

    private void events() {
        eventAddConstraint(binding.tvAddConstraintLeft, "Left_toLeftOf", "Left_toRightOf");
        eventAddConstraint(binding.tvAddConstraintTop, "Top_toTopOf", "Top_toBottomOf");
        eventAddConstraint(binding.tvAddConstraintRight, "Right_toRightOf", "Right_toLeftOf");
        eventAddConstraint(binding.tvAddConstraintBottom, "Bottom_toBottomOf", "Bottom_toTopOf");

        eventSetMargin(binding.btnLeft, "Left");
        eventSetMargin(binding.btnTop, "Top");
        eventSetMargin(binding.btnRight, "Right");
        eventSetMargin(binding.btnBottom, "Bottom");

        eventRatio();

        eventBias(binding.rsBiasV, "Vertical");
        eventBias(binding.rsBiasH, "Horizontal");

        binding.toggleType.addOnButtonCheckedListener(
                (mbt, idBtn, checked) -> {
                    if (checked) {
                        if (idBtn == binding.btnHideAttr.getId()) {
                            binding.linAttrs.removeAllViews();
                        } else if (idBtn == binding.btnRelatvePositioning.getId()) {
                            loadAttrs(1, listRelativePosition);
                        } else if (idBtn == binding.btnMargins.getId()) {
                            loadAttrs(2, listMargins);
                        } else if (idBtn == binding.btnBiasCircular.getId()) {
                            loadAttrs(0, listCircular);
                        } else if (idBtn == binding.btnDimensionsMinMax.getId()) {
                            loadAttrs(0, listDimensionMinMax);
                        } else if (idBtn == binding.btnWeighted.getId()) {
                            loadAttrs(0, listWeighted);
                        }
                    }
                });
    }

    private void loadAttrs(int p, ArrayList<Pair<String, String[]>> list) {
        binding.linAttrs.removeAllViews();
        for (var pair : list) {
            final AttrViewAdapter attr = new AttrViewAdapter(C, element, pair.first, pair.second);
            attr.setDeleteBtnVisible(false);
            attr.setAutoRemoveAttrIfEmpty(true);
            attr.setOnAttrChangedListener(
                    new AttrViewAdapter.OnAttrChangedListener() {
                        @Override
                        public void onChanged() {
                            loadAttrs((int) attr.getView().getTag());
                            listener.onChange();
                        }
                    });
            binding.linAttrs.addView(attr.getView());
            attr.getView().setTag(p);
        }
    }

    private void loadAttrs(int p) {
        switch (p) {
            case 1:
                loadConstraints();
                break;
            case 2:
                loadMargins();
                break;
        }
    }

    public void eventBias(final RangeSlider rs, final String val) {
        rs.setTag(val);
        rs.addOnChangeListener(
                (rangeSlider, f, fromUser) -> {
                    final String value = (String) rangeSlider.getTag();
                    if (fromUser) {
                        if (f == 50.0 || f == 50) {
                            element.removeAttribute("app:layout_constraint" + value + "_bias");
                        } else {
                            element.setAttribute(
                                    "app:layout_constraint" + value + "_bias", "" + (f / 100));
                        }
                        if (listener != null) listener.onChange();
                    }
                });
    }

    public void eventRatio() {
        String value = element.getAttribute("app:layout_constraintDimensionRatio");
        binding.tieRatio.setText(value.trim().isEmpty() ? "1:1" : value);
        binding.tieRatio.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(
                            CharSequence arg0, int arg1, int arg2, int arg3) {}

                    @Override
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                    @Override
                    public void afterTextChanged(Editable edit) {
                        if (edit.toString().trim().isEmpty() || edit.toString().contains("1:1"))
                            element.removeAttribute("app:layout_constraintDimensionRatio");
                        else
                            element.setAttribute(
                                    "app:layout_constraintDimensionRatio", edit.toString());
                        if (listener != null) listener.onChange();
                        loadRatio();
                    }
                });
    }

    private void eventSetMargin(final MaterialButton btn, final String val) {
        btn.setTag(val);
        btn.setOnClickListener(
                v -> {
                    final String value = (String) v.getTag();
                    final DialogAutoComplete dialog = new DialogAutoComplete(C);
                    dialog.setTitle(C.getString(R.string.value));
                    dialog.setHint("android:layout_margin" + value);
                    dialog.setInfo(". . .");
                    dialog.setMsgError(C.getString(R.string.invalide_entry));
                    dialog.setDefaultValue(
                            element.getAttribute("android:layout_margin" + value).trim().isEmpty()
                                    ? "16dp"
                                    : element.getAttribute("android:layout_margin" + value).trim());
                    dialog.withConfirmation(true);
                    dialog.setListForAutoCompletion(AttrViewDataAdapter.getListAssist("@dimen"));
                    dialog.setOnChangeDetected(
                            new DialogAutoComplete.OnChangeDetected() {
                                private String tmpValue = "";

                                @Override
                                public boolean onTextChanged(String text) {
                                    tmpValue = text.trim();
                                    return (ResourcesValuesFixer.getDimen(C, text) != -1);
                                }

                                @Override
                                public void onValueConfirmed(boolean match) {
                                    if (match) {
                                        if (tmpValue.isEmpty() || tmpValue.equals("16dp"))
                                            element.removeAttribute(
                                                    "android:layout_margin" + value);
                                        else
                                            element.setAttribute(
                                                    "android:layout_margin" + value, tmpValue);
                                        loadMargins();
                                        if (listener != null) listener.onChange();
                                    }
                                }
                            });
                    dialog.show();
                });
    }

    private void eventAddConstraint(final TextView tvConstraint, final String V1, final String V2) {
        tvConstraint.setTag(new Pair<>(V1, V2));
        tvConstraint.setOnClickListener(
                v -> {
                    final var pair = (Pair<?, ?>) v.getTag();
                    final String v1 = (String) pair.first;
                    final String v2 = (String) pair.second;
                    if (!((TextView) v).getText().toString().isEmpty()) {
                        final PopupMenu popupMenu = new PopupMenu(C, tvConstraint);
                        popupMenu.getMenu().add(Menu.NONE, 1, Menu.NONE, " ... " + V1);
                        popupMenu.getMenu().add(Menu.NONE, 2, Menu.NONE, " ... " + V2);
                        popupMenu.setOnMenuItemClickListener(
                                item -> {
                                    final String value = (item.getItemId() == 1) ? v1 : v2;
                                    final DialogAutoComplete dialog = new DialogAutoComplete(C);
                                    dialog.setDefaultValue("parent");
                                    dialog.setTitle(C.getString(R.string.value));
                                    dialog.setHint("app:layout_constraint" + value);
                                    dialog.setInfo(
                                            "A view ID is required (default value : parent)");
                                    dialog.setMsgError(C.getString(R.string.invalide_entry));
                                    dialog.withConfirmation(true);
                                    dialog.setListForAutoCompletion(
                                            Arrays.asList(Arrays.copyOf(listId, listId.length)));
                                    dialog.setOnChangeDetected(
                                            new DialogAutoComplete.OnChangeDetected() {
                                                private String tmpValue = "";

                                                @Override
                                                public boolean onTextChanged(String text) {
                                                    tmpValue = text.trim();
                                                    if (Arrays.asList(listId).contains(text))
                                                        return true;
                                                    else if (text.equals("parent")) return true;
                                                    return false;
                                                }

                                                @Override
                                                public void onValueConfirmed(boolean match) {
                                                    if (match) {
                                                        if (tmpValue.isEmpty()) {
                                                            element.removeAttribute(
                                                                    "app:layout_constraint"
                                                                            + value);
                                                        } else {
                                                            element.setAttribute(
                                                                    "app:layout_constraint" + value,
                                                                    tmpValue);
                                                        }
                                                    }
                                                    loadConstraints();
                                                    if (listener != null) listener.onChange();
                                                }
                                            });
                                    dialog.show();
                                    return true;
                                });
                        popupMenu.show();
                    } else {
                        final PopupMenu pop = new PopupMenu(C, v);
                        pop.setForceShowIcon(true);
                        pop.getMenu()
                                .add(Menu.NONE, 1, Menu.NONE, R.string.delete)
                                .setIcon(R.drawable.ic_delete)
                                .setIconTintList(
                                        ColorStateList.valueOf(
                                                ResourcesValuesFixer.getColor(
                                                        C, "?colorOnSurface")));
                        pop.setOnMenuItemClickListener(
                                item -> {
                                    DialogBuilder.getDialogBuilder(
                                                    C,
                                                    C.getString(R.string.warning),
                                                    C.getString(R.string.warning_delete)
                                                            + " Constraint\n"
                                                            + v1
                                                            + " and/or "
                                                            + v2
                                                            + " ?")
                                            .setNegativeButton(R.string.cancel, null)
                                            .setPositiveButton(
                                                    R.string.delete,
                                                    (d, k) -> {
                                                        element.removeAttribute(
                                                                "app:layout_constraint" + v1);
                                                        element.removeAttribute(
                                                                "app:layout_constraint" + v2);
                                                        loadConstraints();
                                                        if (listener != null) listener.onChange();
                                                    })
                                            .show();
                                    return true;
                                });
                        pop.show();
                    }
                });
    }

    private void init() {
        binding = DialogLayoutConstraintLayoutBinding.inflate(LayoutInflater.from(C));
        this.BSD = new BottomSheetDialog(C);
        BSD.setTitle("ConstraintLayout");
        BottomSheetBehavior behavior = BSD.getBehavior();
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setDraggable(false);
        BSD.setContentView((View) binding.getRoot());
        themes();
    }

    public void show(Element e) {
        this.element = e;
        init();
        initId();
        initAdapter();
        loadData();
        events();
        BSD.show();
    }

    private void themes() {
        ViewUtils.setBackground(
                binding.relAddConstraints,
                Color.TRANSPARENT,
                3,
                ResourcesValuesFixer.getColor(C, "?colorOnSurface"),
                0f);
    }

    private void initAdapter() {
        // Relative Position
        listRelativePosition.clear();
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintLeft_toLeftOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintLeft_toRightOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintRight_toLeftOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintRight_toRightOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintTop_toTopOf", Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintTop_toBottomOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintBottom_toTopOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintBottom_toBottomOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintBaseline_toBaselineOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintStart_toEndOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintStart_toStartOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintEnd_toStartOf",
                        Arrays.copyOf(listId, listId.length)));
        listRelativePosition.add(
                new Pair<>(
                        "app:layout_constraintEnd_toEndOf", Arrays.copyOf(listId, listId.length)));

        // Margins
        listMargins.clear();
        listMargins.add(new Pair<>("app:layout_marginBaseline", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginStart", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginEnd", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginLeft", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginTop", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginRight", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginBottom", new String[] {"@dimen"}));
        listMargins.add(new Pair<>("app:layout_goneMarginBaseline", new String[] {"@dimen"}));

        // Circular
        listCircular.clear();
        listCircular.add(
                new Pair<>("app:layout_constraintCircle", Arrays.copyOf(listId, listId.length)));
        listCircular.add(new Pair<>("app:layout_constraintCircleRadius", new String[] {"@dimen"}));
        listCircular.add(new Pair<>("app:layout_constraintCircleAngle", null));

        // Dimension - Min & Max
        listDimensionMinMax.clear();
        listDimensionMinMax.add(
                new Pair<>("app:layout_constrainedWidth", AllAttrBase.VALUES_BOOLEAN));
        listDimensionMinMax.add(
                new Pair<>("app:layout_constrainedHeight", AllAttrBase.VALUES_BOOLEAN));
        listDimensionMinMax.add(
                new Pair<>("app:layout_constraintWidth_min", new String[] {"@dimen"}));
        listDimensionMinMax.add(
                new Pair<>("app:layout_constraintHeight_min", new String[] {"@dimen"}));
        listDimensionMinMax.add(
                new Pair<>("app:layout_constraintWidth_max", new String[] {"@dimen"}));
        listDimensionMinMax.add(
                new Pair<>("app:layout_constraintHeight_max", new String[] {"@dimen"}));
        listDimensionMinMax.add(new Pair<>("app:layout_constraintWidth_percent", null));
        listDimensionMinMax.add(new Pair<>("app:layout_constraintHeight_percent", null));
        listDimensionMinMax.add(
                new Pair<>(
                        "app:layout_constraintWidth_default",
                        new String[] {"none", "percent", "wrap"}));
        listDimensionMinMax.add(
                new Pair<>(
                        "app:layout_constraintHeight_default",
                        new String[] {"none", "percent", "wrap"}));

        // Weighted
        listWeighted.clear();
        listWeighted.add(new Pair<>("app:layout_constraintHorizontal_weight", null));
        listWeighted.add(new Pair<>("app:layout_constraintVertical_weight", null));
    }

    private void initId() {
        final ArrayList<String> list = new ArrayList<>();
        for (Element e :
                XmlManager.getAllFirstChildFromElement((Element) element.getParentNode())) {
            String id = e.getAttribute("android:id");
            if (id.startsWith("@+id/")) {
                list.add(id);
                list.add("@id/" + id.split("\\/")[1]);
            }
        }
        listId = new String[list.size()];
        for (int i = 0; i < list.size(); i++) listId[i] = list.get(i);
    }

    public void setOnChangeListener(OnChangedListener listener) {
        this.listener = listener;
    }

    public interface OnChangedListener {
        public void onChange();
    }
}
