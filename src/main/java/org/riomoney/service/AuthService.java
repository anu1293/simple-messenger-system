package org.riomoney.service;

import org.riomoney.model.*;

public interface AuthService {
    CreateUserResponse signup(CreateUserRequest request);

    LoginResponse signin(LoginRequest request);

    boolean signout(String token);
}
