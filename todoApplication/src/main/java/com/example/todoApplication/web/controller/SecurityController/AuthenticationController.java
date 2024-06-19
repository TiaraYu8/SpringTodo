package com.example.todoApplication.web.controller.SecurityController;

import com.example.todoApplication.common.model.web.form.SignInRequest;
import com.example.todoApplication.common.model.web.form.SignUpRequest;
import com.example.todoApplication.common.model.web.response.JwtAuthenticationResponse;
import com.example.todoApplication.service.UserServices;
import com.example.todoApplication.service.authService.AuthenticationService;
import com.example.todoApplication.service.jwtservice.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@Valid @RequestBody SignUpRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.signUp(registerRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

//    @Autowired
    private JwtService jwtService;
    private final UserServices userServices;

    @GetMapping("/some-secured-endpoint")
    public ResponseEntity<String> someSecuredEndpoint(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header is missing or invalid");
        }
        try {
            String jwt = authHeader.substring(7);
            String userName = jwtService.extractUserName(jwt);
            UserDetails userDetails = userServices.userDetailsService().loadUserByUsername(userName);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                return ResponseEntity.ok("Access granted");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
