package com.medialab_challenge.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.medialab_challenge.R;
import com.medialab_challenge.databinding.DialogAddUserBinding;
import com.medialab_challenge.util.UserViewUtils;
import com.medialab_challenge.vo.User;

public class DialogAdduser extends DialogFragment {


    private DialogListenerAddUser listener;
    DialogAddUserBinding binding;

    public DialogAdduser(DialogListenerAddUser listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.dialog_add_user, container, false);
        binding.setValidEmail(true);
        binding.btCancelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btAddUser.setOnClickListener(view -> {
            addUser();
        });

        binding.tilEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.setValidEmail(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return binding.getRoot();
    }

    private void addUser() {
        if (!binding.tilName.getEditText().getText().equals("")
                && UserViewUtils.isValidEmail(binding.tilEmail.getEditText().getText())) {
            User user = new User();
            user.name = binding.tilName.getEditText().getText().toString();
            user.email = binding.tilEmail.getEditText().getText().toString();
            this.listener.onClickSave(user);
            binding.setValidEmail(true);
        } else {
            binding.setValidEmail(false);
        }

    }

    public interface DialogListenerAddUser {
        public void onClickSave(User user);
    }


    public void setListener(DialogListenerAddUser listener) {
        this.listener = listener;
    }
}