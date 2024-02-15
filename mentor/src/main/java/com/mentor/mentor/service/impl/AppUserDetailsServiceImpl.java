package com.mentor.mentor.service.impl;

import com.mentor.mentor.exception.ApplicationException;
import com.mentor.mentor.entity.User;
import com.mentor.mentor.repo.UserRepository;
import com.mentor.mentor.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("AppUserDetailsService")
@AllArgsConstructor(onConstructor = @__({@org.springframework.beans.factory.annotation.Autowired}))
public class AppUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(null));
        return new UserPrincipal(user);
    }
}
