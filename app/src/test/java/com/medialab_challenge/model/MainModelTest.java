package com.medialab_challenge.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.medialab_challenge.business.MainActivityModel;
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
public class MainModelTest {
    @Mock
    Repository repository;

    private MainActivityModel model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        model = new MainActivityModel();
    }

    @Test
    public void whenGetUserList_andHaveUsers_shouldReturnValue() {
        List<User> userList = getUsers();

        when(repository.getListUsers()).thenReturn(userList);
        assertNotNull(userList);
        assertEquals(userList.get(2).email, "leo@email.com");
        assertEquals(userList.size(), 4);

    }
    @Test
    public void whenAddUserInList_andGetUser_shouldReturnValue() {
        List<User> userList = getUsers();

        when(repository.getListUsers()).thenReturn(userList);
        userList.add(adduser());
        assertNotNull(userList);
        assertEquals(userList.get(4).email, "caua@email.com");
        assertEquals(userList.size(), 5);

    }
    @Test
    public void whenDeleteUserandGetUser_shouldReturnValue() {
        List<User> userList = getUsers();

        when(repository.getListUsers()).thenReturn(userList);
        userList.remove(2);
        assertNotNull(userList);
        assertEquals(userList.get(2).email, "ana@email.com");
        assertEquals(userList.size(), 3);

    }

    private List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Cristiano", "cristiano@email.com", null));
        userList.add(new User(2, "Amanda", "amanda@email.com", null));
        userList.add(new User(3, "Leo", "leo@email.com", null));
        userList.add(new User(4, "Ana", "ana@email.com", null));
        return userList;
    }

    private User adduser(){
        return new User(5, "Caua", "caua@email.com", null);
    }
}
