package com.mentor.mentor.service;

import com.mentor.mentor.dto.SignInForm;
import com.mentor.mentor.dto.SignUpForm;
import com.mentor.mentor.dto.UserView;
import com.mentor.mentor.model.User;
import com.mentor.mentor.repo.UserRepository;
import com.mentor.mentor.security.UserPrincipal;
import com.mentor.mentor.security.jwt.JwtUtils;
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


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

     AuthenticationManager authenticationManager;
    BCryptPasswordEncoder passwordEncoder;
    UserRepository userRepository;

    ModelMapper modelMapper;


    JwtUtils jwtUtils;

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
                .role(roles.get(0))
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(userView);

    }

    public UserView saveUser(SignUpForm request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User newUser = userRepository.saveAndFlush(user);
        return modelMapper.map(newUser, UserView.class);
    }
}
