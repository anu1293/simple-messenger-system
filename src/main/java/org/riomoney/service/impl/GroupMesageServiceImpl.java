package org.riomoney.service.impl;

import org.riomoney.entities.*;
import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.repositories.*;
import org.riomoney.service.GroupMesageService;
import org.riomoney.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class GroupMesageServiceImpl implements GroupMesageService {
    @Autowired
    UserMessageRepository userMessageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserGroupsRepository userGroupsRepository;
    @Autowired
    JwtService jwtService;
    @Override
    public TextMessageResponse sendMessage(String authorization,TextMessageObject textMessageObject) throws GroupNotFoundException {
        Optional<GroupEntity> to = groupRepository.findById(textMessageObject.getTo());
        if(to.isEmpty()) {
            throw new GroupNotFoundException("invalid group id :"+textMessageObject.getTo());
        }
        String sender = jwtService.extractUserName(authorization.substring(7));
        UserEntity from = userRepository.findByUserName(sender);

        MessageEntity message = new MessageEntity();
        message.setMessage(textMessageObject.getText());
        message.setSender(from);
        message.setTimestamp(Timestamp.from(Instant.now()));
        message.setGroup(to.get());
        message = messageRepository.save(message);

        List<UserEntity> users = userGroupsRepository.findUsersByGroupId(to.get())
                .stream()
                .map(userGroup -> userGroup.getId().getUser())
                .toList();

        MessageEntity finalMessage = message;

        users.forEach(user ->{
                    UserMessageReadStatusEntityId id = new UserMessageReadStatusEntityId();
                    id.setUser(user);
                    id.setMessage(finalMessage);
                    UserMessageReadStatusEntity userMessageReadStatusEntity = new UserMessageReadStatusEntity();
                    userMessageReadStatusEntity.setId(id);
                    userMessageReadStatusEntity.setRead(user.equals(from));
                    userMessageRepository.save(userMessageReadStatusEntity);
                });
        return new TextMessageResponse().status("SUCCESS").text("Message sent successfully");
    }
}
