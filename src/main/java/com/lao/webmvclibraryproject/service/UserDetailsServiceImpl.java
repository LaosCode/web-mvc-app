package com.lao.webmvclibraryproject.service;

import com.lao.webmvclibraryproject.model.User;
import com.lao.webmvclibraryproject.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByName(username);

        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");

        return new UserDetailsImpl(user.get());
    }
}
