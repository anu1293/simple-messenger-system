package org.riomoney.service;

import org.riomoney.model.CreateUserRequest;
import org.riomoney.model.CreateUserResponse;
import org.riomoney.model.LoginRequest;
import org.riomoney.model.LoginResponse;

public interface AuthService {
    CreateUserResponse signup(CreateUserRequest request);

    LoginResponse signin(LoginRequest request);
}
