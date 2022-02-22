package com.medialab_challenge.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.medialab_challenge.R;
import com.medialab_challenge.databinding.ActivityMainBinding;
import com.medialab_challenge.viewmodel.MainActivityViewModel;
import com.medialab_challenge.vo.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogAdduser.DialogListenerAddUser {
    private static final int DIALOG_ADD_USER = 1;
    ActivityMainBinding binding;
    MainActivityViewModel viewModel;
    DialogAdduser dialogAdduser;
    public static final String TAG = "add_user_dialog";
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        dialogAdduser = new DialogAdduser(this);

        binding.setLifecycleOwner(this);
        binding.flaAddUser.setOnClickListener(view -> {
            addUser();
        });

        viewModel.userMutableLiveData.observe(this, users -> {
            if (users != null) {
                setAdapter(users);
            }
        });
    }

    private void addUser() {
        dialogAdduser.show(getSupportFragmentManager(), TAG);
    }

    private void setAdapter(List<User> userList) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new UserListAdapter(userList, this, viewModel);
        binding.rvUserList.setLayoutManager(layoutManager);
        binding.rvUserList.setAdapter(adapter);

    }

    @Override
    public void onClickSave(User user) {
        viewModel.addUser(user);
        viewModel.getUserListLiveData();
        dialogAdduser.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getUserListLiveData();
    }
}