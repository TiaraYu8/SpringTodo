package com.example.todoApplication.web.controller.SecurityController;

import com.example.todoApplication.common.model.web.form.SignInRequest;
import com.example.todoApplication.common.model.web.form.SignUpRequest;
import com.example.todoApplication.common.model.web.response.JwtAuthenticationResponse;
import com.example.todoApplication.service.authService.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.signUp(registerRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
}
