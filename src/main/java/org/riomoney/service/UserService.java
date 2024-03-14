package org.riomoney.service;

import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.UserList;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    public UserList getAllUsers();
    boolean blockUser(int userId, String token);
    UserDetailsService userDetailsService();
}
