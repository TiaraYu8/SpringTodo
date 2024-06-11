package com.example.todoApplication.service.authService;

import com.example.todoApplication.common.model.web.form.SignInRequest;
import com.example.todoApplication.common.model.web.form.SignUpRequest;
import com.example.todoApplication.common.model.web.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);

}
