package org.riomoney.controllers;

import org.apache.coyote.Response;
import org.riomoney.entities.User;
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
    public ResponseEntity<LoginResponse> authLoginPost(LoginRequest loginRequest) {
        System.out.println(userRepository.listUserNames());
        return ResponseEntity.ok(new LoginResponse().status("ok").message("logged in successfully"));
    }

    @Override
    public ResponseEntity<Void> authLogoutPost(LogoutRequest logoutRequest) {
        ResponseEntity<Void> ok = (ResponseEntity<Void>) ResponseEntity.ok();
        return ok;
    }

    @Override
    public ResponseEntity<CreateUserResponse> createPost(CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(new CreateUserResponse().message("user created").status("success"));
    }
}
