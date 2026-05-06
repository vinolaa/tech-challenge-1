package com.github.vinolaa.tc1.security;

import com.github.vinolaa.tc1.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = userRepository.findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException(login));

        String role = user.getClass().getSimpleName().toUpperCase();

        return new User(
            user.getLogin(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}