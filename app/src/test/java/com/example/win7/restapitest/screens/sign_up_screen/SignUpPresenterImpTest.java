package com.example.win7.restapitest.screens.sign_up_screen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz on 2016-05-27.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpPresenterImpTest {
    @Mock
    private SignUpView view;
    private SignUpPresenterImp presenterImp;
    @Before
    public void setUp() throws Exception {
        presenterImp = new SignUpPresenterImp(view);

    }
    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception{
        when(view.getEmail()).thenReturn("");
        presenterImp.onClickSignUp();
        verify(view).setEmptyEmailError();

    }
    @Test
    public void shouldShowErrorMessageWhenEmailIsInvalid() throws Exception{
        when(view.getEmail()).thenReturn("test");
        presenterImp.onClickSignUp();
        verify(view).setInvalidEmailError();
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception{
        when(view.getEmail()).thenReturn("mock@test.com");
        when(view.getPassword()).thenReturn("");
        presenterImp.onClickSignUp();
        verify(view).setEmptyPasswordError();
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsTooShort() throws Exception{
        when(view.getEmail()).thenReturn("mock@test.com");
        when(view.getPassword()).thenReturn("short");
        presenterImp.onClickSignUp();
        verify(view).setPasswordTooShortError();
    }

    @Test
    public void shouldShowErrorMessageWhenDifferentPassword() throws Exception{
        when(view.getEmail()).thenReturn("mock@test.com");
        when(view.getPassword()).thenReturn("password");
        when(view.getPasswordAgain()).thenReturn("anotherpassword");
        presenterImp.onClickSignUp();
        verify(view).setDifferentPasswordError();
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordAgainIsEmpty() throws Exception{
        when(view.getEmail()).thenReturn("mock@test.com");
        when(view.getPassword()).thenReturn("password");
        when(view.getPasswordAgain()).thenReturn("");
        presenterImp.onClickSignUp();
        verify(view).setEmptyPasswordAgainError();
    }

}