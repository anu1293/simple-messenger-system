package org.riomoney.controllers;

import org.riomoney.model.*;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    @Autowired
    AuthService authService;
    @Override
    public ResponseEntity<LoginResponse> signIn(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.signin(loginRequest));
    }

    @Override
    public ResponseEntity<Void> signOut(LogoutRequest logoutRequest) {
        return null;
    }

    @Override
    public ResponseEntity<CreateUserResponse> signUp(CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(authService.signup(createUserRequest));
    }
}
