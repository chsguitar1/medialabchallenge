package com.medialab_challenge.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.medialab_challenge.business.MainActivityModel;
import com.medialab_challenge.business.UserDetailsModel;
import com.medialab_challenge.data.Repository;
import com.medialab_challenge.vo.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class UserDetailsModeTest {
    @Mock
    Repository repository;

    private UserDetailsModel model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        model = new UserDetailsModel();
    }

    @Test
    public void whenGetUserList_andHaveUsers_shouldReturnValue() {
        List<User> userList = getUsers();

        when(repository.getUser(3)).thenReturn(userList.get(2));
        assertNotNull(userList);
        assertEquals(userList.get(2).email, "leo@email.com");
        assertEquals(userList.size(), 4);

    }

    private List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Cristiano", "cristiano@email.com", null));
        userList.add(new User(2, "Amanda", "amanda@email.com", null));
        userList.add(new User(3, "Leo", "leo@email.com", null));
        userList.add(new User(4, "Ana", "ana@email.com", null));
        return userList;
    }
}
