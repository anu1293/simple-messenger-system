package org.riomoney.service.impl;

import lombok.RequiredArgsConstructor;
import org.riomoney.entities.BlockedConversationEntity;
import org.riomoney.entities.BlockedConversationEntityId;
import org.riomoney.entities.UserEntity;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.UserList;
import org.riomoney.repositories.BlockedConversationRepository;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.JwtService;
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
   @Autowired
    BlockedConversationRepository blockedConversationRepository;
   @Autowired
    JwtService jwtService;
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
    public boolean blockUser(int userId, String token) {
        String userName = jwtService.extractUserName(token.substring(7));
        UserEntity user1 = userRepository.findByUserName(userName);
        UserEntity user2 = userRepository.findById(userId);
        if(user2 == null)
            return false;
        BlockedConversationEntityId id= new BlockedConversationEntityId();
        id.setUser1(user1);
        id.setUser2(user2);
        BlockedConversationEntity entity = new BlockedConversationEntity();
        entity.setId(id);
        blockedConversationRepository.save(entity);
        return true;
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
