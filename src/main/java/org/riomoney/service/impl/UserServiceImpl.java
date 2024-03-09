package org.riomoney.service.impl;

import org.riomoney.model.UserList;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
}
