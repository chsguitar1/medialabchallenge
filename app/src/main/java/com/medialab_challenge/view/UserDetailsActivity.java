package com.medialab_challenge.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.medialab_challenge.R;
import com.medialab_challenge.databinding.ActivityUserDetailsActitivyBinding;
import com.medialab_challenge.util.UserViewUtils;
import com.medialab_challenge.viewmodel.UserDetailsViewModel;
import com.medialab_challenge.vo.User;

public class UserDetailsActivity extends AppCompatActivity {
    public static String USER_ID = "userid";
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;
    Bitmap photo;
    User user;

    ActivityUserDetailsActitivyBinding binding;
    UserDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details_actitivy);
        viewModel = new ViewModelProvider(this).get(UserDetailsViewModel.class);
        if (getIntent().getExtras() != null) {
            viewModel.getUserLiveData(getIntent().getExtras().getInt(USER_ID));
            viewModel.userMutableLiveData.observe(this, user -> {
                if (user != null) {
                    setUser(user);
                    binding.setUser(user);
                    if (user.image != null) {
                        binding.imageView.setImageBitmap(user.image);
                    }

                }
            });
        }

        binding.btFromCamera.setOnClickListener(view -> {
            getImageFromCamera();
        });

        binding.btEditUser.setOnClickListener(view -> {
            saveUser();
        });

        binding.tilEmailEdit.getEditText().addTextChangedListener(new TextWatcher() {
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

    }


    public void setUser(User user) {
        this.user = user;
    }

    private void saveUser() {
        if (!binding.tilNameEdit.getEditText().getText().equals("")
                && UserViewUtils.isValidEmail(binding.tilEmailEdit.getEditText().getText())) {
            user.image = photo;
            user.name = binding.tilNameEdit.getEditText().getText().toString();
            user.email = binding.tilEmailEdit.getEditText().getText().toString();
            viewModel.saveUser(user);
            binding.setValidEmail(true);
            finish();
        } else {

            binding.setValidEmail(false);
        }
    }

    private void getImageFromCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            binding.imageView.setImageBitmap(photo);

        }
    }
}
