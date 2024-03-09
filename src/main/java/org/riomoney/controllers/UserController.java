package org.riomoney.controllers;

import org.riomoney.model.UserList;
import org.riomoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UserController implements UsersApi{
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<UserList> fetchAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
