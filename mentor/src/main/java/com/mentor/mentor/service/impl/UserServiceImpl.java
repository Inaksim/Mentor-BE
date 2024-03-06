package com.mentor.mentor.service.impl;

import com.mentor.mentor.dto.form.ResetPasswordForm;
import com.mentor.mentor.dto.form.SignInForm;
import com.mentor.mentor.dto.form.SignUpForm;
import com.mentor.mentor.dto.form.UpdateUserForm;
import com.mentor.mentor.dto.view.UserView;
import com.mentor.mentor.exception.ApplicationException;
import com.mentor.mentor.entity.User;
import com.mentor.mentor.repo.UserRepository;
import com.mentor.mentor.security.Role;
import com.mentor.mentor.security.UserPrincipal;
import com.mentor.mentor.security.jwt.JwtUtils;
import com.mentor.mentor.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import static com.mentor.mentor.exception.Errors.*;
import static com.mentor.mentor.utils.Constraints.ID;
import static com.mentor.mentor.utils.Constraints.EMAIL;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__({@org.springframework.beans.factory.annotation.Autowired}))
@Service
public class UserServiceImpl implements UserService {

    AuthenticationManager authenticationManager;
    BCryptPasswordEncoder passwordEncoder;
    UserRepository userRepository;

    ModelMapper modelMapper;


    private final JwtUtils jwtUtils;

    public ResponseEntity<UserView> authenticateUser(SignInForm request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UserView userView = UserView
                .builder()
                .id(userDetails.getId())
                .email(userDetails.getUsername())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .role(userDetails.getRole())
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(userView);

    }

    public UserView saveUser(SignUpForm request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User newUser = userRepository.saveAndFlush(user);
        return modelMapper.map(newUser, UserView.class);
    }

    public List<UserView> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserView.class)).collect(Collectors.toList());
    }

    public UserView getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, Collections.singletonMap(ID, id)));
        return modelMapper.map(user, UserView.class);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<Void> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    public UserView updateUser(UpdateUserForm form) {
        User user = userRepository.findById(form.getId()).orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, Collections.singletonMap(ID, form.getId())));

        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setRole(Role.valueOf(form.getRole()));
        user.setEmail(form.getEmail());

        User result = userRepository.saveAndFlush(user);
        return modelMapper.map(result, UserView.class);

    }

    public void resetPassword(ResetPasswordForm form) {
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(() -> new ApplicationException(USER_NOT_FOUND_BY_EMAIL, Collections.singletonMap(EMAIL, form.getEmail())));
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.saveAndFlush(user);

    }




}
