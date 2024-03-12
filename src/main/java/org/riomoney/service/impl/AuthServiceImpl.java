package org.riomoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.riomoney.entities.UserEntity;
import org.riomoney.model.CreateUserRequest;
import org.riomoney.model.CreateUserResponse;
import org.riomoney.model.LoginRequest;
import org.riomoney.model.LoginResponse;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.AuthService;
import org.riomoney.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public CreateUserResponse signup(CreateUserRequest request) {
        if(StringUtils.equals(request.getPassword(),request.getConfirmPassword())) {
            var user = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).build();
            UserEntity entity = new UserEntity();
            entity.setUserName(user.getUsername());
            entity.setPassword(user.getPassword());
            userRepository.save(entity);
            var jwt = jwtService.createToken(user);
            return new CreateUserResponse().token(jwt).message("signup successful").status("SUCCESS");
        }
        return  new CreateUserResponse().token(null).message("signup failed").status("FAILED");
    }

    @Override
    public LoginResponse signin(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity userEnity = userRepository.findByUserName(request.getUsername());
            if(userEnity == null)
                throw new IllegalArgumentException("Invalid username or password.");
        var user = User.builder().username(userEnity.getUsername()).password(userEnity.getPassword()).build();
            var jwt = jwtService.createToken(user);
        return new LoginResponse().token(jwt).status("SUCCESS").message("LOGGED IN SUCCESSFULLY");
    }
}
