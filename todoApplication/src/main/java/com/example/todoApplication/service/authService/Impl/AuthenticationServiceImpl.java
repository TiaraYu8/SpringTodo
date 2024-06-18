package com.example.todoApplication.service.authService.Impl;

import com.example.todoApplication.common.model.database.Role;
import com.example.todoApplication.common.model.database.UserModel;
import com.example.todoApplication.common.model.web.form.SignInRequest;
import com.example.todoApplication.common.model.web.form.SignUpRequest;
import com.example.todoApplication.common.model.web.response.JwtAuthenticationResponse;
import com.example.todoApplication.repository.UserRepository;
import com.example.todoApplication.service.authService.AuthenticationService;
import com.example.todoApplication.service.jwtservice.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = UserModel.builder().username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        new JwtAuthenticationResponse();
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
