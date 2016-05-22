package com.example.win7.restapitest.screens.login_screen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz on 2016-05-22.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterImpTest {
    @Mock
    private LoginView view;
    private LoginPresenterImp presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenterImp(view);

    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {

        when(view.getEmail()).thenReturn("");
        presenter.onClickLogin();
        verify(view).setEmptyEmailError();
    }
    
}