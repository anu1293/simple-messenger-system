package org.riomoney.controllers;

import org.riomoney.model.*;
import org.riomoney.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<LoginResponse> signIn(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> signOut(LogoutRequest logoutRequest) {
        return null;
    }

    @Override
    public ResponseEntity<CreateUserResponse> signUp(CreateUserRequest createUserRequest) {
        return null;
    }
}
