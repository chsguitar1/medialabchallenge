package com.medialab_challenge.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medialab_challenge.business.IMainActivityModel;
import com.medialab_challenge.business.MainActivityModel;
import com.medialab_challenge.vo.User;

import java.util.List;


public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<List<User>> userMutableLiveData = new MutableLiveData<>();

    private IMainActivityModel model = MainActivityModel.getInstance();

    public void addUser(User user) {
        model.addUser(user);
    }

    public void getUserListLiveData(){
       userMutableLiveData.setValue(model.getUserList());
    }

    public void deleteUser(User user){
        model.deleteUser(user);

    }

}
