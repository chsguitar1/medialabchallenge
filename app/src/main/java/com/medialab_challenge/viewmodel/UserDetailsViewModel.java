package com.medialab_challenge.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medialab_challenge.business.IUserDetailsModel;
import com.medialab_challenge.business.UserDetailsModel;
import com.medialab_challenge.vo.User;

public class UserDetailsViewModel extends ViewModel {
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private IUserDetailsModel model = UserDetailsModel.getInstance();

    public void getUserLiveData(Integer userId) {
        userMutableLiveData.setValue(model.getUser(userId));
    }

    public void saveUser(User user){
        model.saveUser(user);
    }
}
