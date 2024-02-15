package com.mentor.mentor.service;

import com.mentor.mentor.dto.form.ResetPasswordForm;
import com.mentor.mentor.dto.form.SignInForm;
import com.mentor.mentor.dto.form.SignUpForm;
import com.mentor.mentor.dto.form.UpdateUserForm;
import com.mentor.mentor.dto.view.UserView;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {
    List<UserView> getAllUsers();

    UserView getUserById(@NonNull Long id);

    ResponseEntity<UserView> authenticateUser( @NonNull SignInForm model);

    ResponseEntity<Void> logoutUser();
    UserView saveUser( @NonNull SignUpForm model);
    UserView updateUser( @NonNull UpdateUserForm model);

    void deleteUser(@NonNull Long id);

    void resetPassword( @NonNull ResetPasswordForm model);
}
