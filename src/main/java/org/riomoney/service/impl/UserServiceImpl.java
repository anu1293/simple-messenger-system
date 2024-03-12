package org.riomoney.service.impl;

import lombok.RequiredArgsConstructor;
import org.riomoney.entities.UserEntity;
import org.riomoney.model.UserList;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
   @Autowired
    UserRepository userRepository;
    @Override
    public UserList getAllUsers() {
        List<String> userNames = userRepository.listUserNames();
        UserList userList = new UserList();
        if(CollectionUtils.isEmpty(userNames)) {
            userList.setStatus("no users found");
        } else {
            userList.setStatus("SUCCESS");
            userList.setData(userNames);
        }
        return userList;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                String userName,password = null;
                UserEntity user = userRepository.findByUserName(username);
                if(user == null) {
                   throw new UsernameNotFoundException("User not found");
                } else {
                    userName = user.getUserName();
                    password = user.getPassword();
                }
                return new User(userName,password, Collections.EMPTY_LIST);
            }
        };
    }
}
