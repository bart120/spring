package com.formation.web.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formation.models.User;
import com.formation.repo.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        var grant = u.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).toList();

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(u.getPassword())
                .disabled(!u.isEnabled())
                .authorities(grant)
                .build();
    }

}
