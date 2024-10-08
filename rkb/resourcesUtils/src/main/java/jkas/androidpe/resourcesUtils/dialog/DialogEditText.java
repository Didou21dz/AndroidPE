package jkas.androidpe.resourcesUtils.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import jkas.androidpe.resources.R;
import jkas.androidpe.resourcesUtils.databinding.DialogEditTextBinding;

/**
 * @author JKas
 */
public class DialogEditText {
    private OnChangeDetected listener;
    private Context C;
    private DialogEditTextBinding binding;
    private MaterialAlertDialogBuilder builder;
    private String pattern, msgError, text = "";
    private boolean withConfirmation = false;

    public DialogEditText(Context c) {
        C = c;
        init();
    }

    public void setTitle(@NonNull String title) {
        builder.setTitle(title);
    }

    public void setHint(String hint) {
        binding.til.setHint(hint);
    }

    public void setDefaultValue(@NonNull String value) {
        binding.textInput.setText(value);
        text = value;
    }

    public void withConfirmation(boolean withConfirmation) {
        this.withConfirmation = withConfirmation;
    }

    public void setInfo(String info) {
        binding.tvInfo.setText(info);
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public void setPattern(@Nullable String pattern) {
        this.pattern = pattern;
    }

    public void show() {
        events();
        builder.show();
    }

    private void events() {
        if (withConfirmation) {
            builder.setNegativeButton(R.string.cancel, null);
            builder.setPositiveButton(
                    R.string.save,
                    (d, v) -> {
                        listener.onTextChanged(binding.textInput.getText().toString());
                        if (pattern != null) {
                            if (text.matches(pattern) || (text + "rkb").matches(pattern))
                                listener.onValueConfirmed(true);
                            else {
                                listener.onValueConfirmed(false);
                            }
                        } else listener.onValueConfirmed(true);
                    });
        }

        binding.textInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(
                            CharSequence arg0, int arg1, int arg2, int arg3) {}

                    @Override
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

                    @Override
                    public void afterTextChanged(Editable edit) {
                        binding.til.setError(null);
                        if (pattern == null) return;
                        text = edit.toString();
                        if (text.matches(pattern)) {
                            listener.onTextChanged(text);
                            if (!withConfirmation) listener.onValueConfirmed(true);
                        } else if ((text + "rkb").matches(pattern)) {
                            listener.onTextChanged(text);
                            if (!withConfirmation) listener.onValueConfirmed(true);
                        } else {
                            listener.onValueConfirmed(false);
                            binding.til.setError(msgError);
                        }
                    }
                });
    }

    private void init() {
        binding = DialogEditTextBinding.inflate(LayoutInflater.from(C));
        builder = new MaterialAlertDialogBuilder(C);
        builder.setView(binding.getRoot());
    }

    public void setOnChangeDetected(OnChangeDetected listener) {
        this.listener = listener;
    }

    public interface OnChangeDetected {
        public void onTextChanged(String text);

        public void onValueConfirmed(boolean match);
    }
}
