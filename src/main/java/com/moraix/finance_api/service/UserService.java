package com.moraix.finance_api.service;

import com.moraix.finance_api.exception.ApiException;
import com.moraix.finance_api.model.User;
import com.moraix.finance_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new ApiException("Email já cadastrado!", HttpStatus.CONFLICT);
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("Usuário não encontrado!", HttpStatus.NOT_FOUND));
    }
}