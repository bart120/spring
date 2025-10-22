package com.formation.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.formation.models.Role;
import com.formation.models.User;
import com.formation.repo.RoleRepository;
import com.formation.repo.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // roles
        var userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));
        var adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ADMIN")));

        // user admin
        if (!userRepository.existsByUsername("admin")) {
            var adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setEnabled(true);
            adminUser.addRole(adminRole);
            adminUser.addRole(userRole);
            userRepository.save(adminUser);
        }

        // user toto
        if (!userRepository.existsByUsername("toto")) {
            var normalUser = new User();
            normalUser.setUsername("toto");
            normalUser.setPassword(passwordEncoder.encode("user123"));
            normalUser.setEnabled(true);
            normalUser.addRole(userRole);
            userRepository.save(normalUser);
        }

    }
}
