package org.riomoney.service;

import org.riomoney.model.UserList;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    public UserList getAllUsers();
    UserDetailsService userDetailsService();
}
