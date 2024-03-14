package org.riomoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.riomoney.entities.*;
import org.riomoney.exceptions.BlockedConversationException;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessage;
import org.riomoney.model.UserMessages;
import org.riomoney.repositories.BlockedConversationRepository;
import org.riomoney.repositories.MessageRepository;
import org.riomoney.repositories.UserMessageRepository;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.JwtService;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    UserMessageRepository userMessageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    BlockedConversationRepository blockedConversationRepository;
    @Autowired
    JwtService jwtService;

    @Override
    public UserMessages getUserMessages(String token, String from) throws UserNotFoundException {
        UserEntity sender = userRepository.findByUserName(from);
        String receiver = jwtService.extractUserName(token.substring(7));
        UserEntity user = userRepository.findByUserName(receiver);
        List<UserMessageReadStatusEntity> userMessageReadStatusEntity;
        if(StringUtils.isNotBlank(from)) {
            if(sender == null) {
                throw new UserNotFoundException("receiver userid is invalid");
            }
            userMessageReadStatusEntity =  userMessageRepository.fetchUserUnreadMessages(user,sender);
        } else {
            userMessageReadStatusEntity = userMessageRepository.fetchUserUnreadMessages(user);
        }
        return getUserMessagesFromEntity(userMessageReadStatusEntity);
    }

    @Override
    public TextMessageResponse sendMessage(String token, TextMessageObject textMessageObject) throws UserNotFoundException, BlockedConversationException {
        String sender = jwtService.extractUserName(token.substring(7));
        Optional<UserEntity> to = userRepository.findById(textMessageObject.getTo());
        if(to.isEmpty()) {
            throw new UserNotFoundException("receiver userid is invalid");
        }
        UserEntity from = userRepository.findByUserName(sender);
        // add a check here if either user has blocked the other, if yes, then throw exception
        if(checkForBlockedConversation(to.get(),from)) {
            throw new BlockedConversationException("senders conversation with receiver is blocked");
        }
        MessageEntity message = new MessageEntity();
        message.setMessage(textMessageObject.getText());
        message.setSender(from);
        message.setTimestamp(Timestamp.from(Instant.now()));
        message = messageRepository.save(message);
        UserMessageReadStatusEntityId id = new UserMessageReadStatusEntityId();
        id.setUser(to.get());
        id.setMessage(message);
        UserMessageReadStatusEntity userMessageReadStatusEntity = new UserMessageReadStatusEntity();
        userMessageReadStatusEntity.setId(id);
        userMessageReadStatusEntity.setRead(false);
        userMessageRepository.save(userMessageReadStatusEntity);
        return new TextMessageResponse().status("SUCCESS").text("Message sent successfully");
    }

    private UserMessages getUserMessagesFromEntity(List<UserMessageReadStatusEntity> userMessageReadStatusEntity) {
        UserMessages userMessages = new UserMessages();
        List<UserMessage> data = userMessageReadStatusEntity
        .stream()
        .collect(Collectors.groupingBy(msg -> getSender(msg.getId().getMessage()),Collectors.mapping(msg->msg.getId().getMessage(), Collectors.toList())
        )).entrySet().stream().map(entry -> new UserMessage().username(retrieveUsername(entry.getKey())).texts(getMessages(entry.getValue())).groupName(retrieveGroupName(entry.getKey())))
        .collect(Collectors.toList());

        userMessages.setStatus("SUCCESS");
        userMessages.setMessage(CollectionUtils.isEmpty(data)?"no new message available":"new messages available");
        userMessages.setData(data);
        updateLastMessageReadByUser(userMessageReadStatusEntity);
        return userMessages;
    }
    @Async
    private void updateLastMessageReadByUser(List<UserMessageReadStatusEntity> userMessagesReadInfoEntity) {
        userMessagesReadInfoEntity.forEach(userMessage -> userMessage.setRead(true));
        userMessageRepository.saveAll(userMessagesReadInfoEntity);
    }
    private List<String> getMessages(List<MessageEntity> entity) {
        return entity.stream().map(MessageEntity::getMessage).collect(Collectors.toList());
    }

    private String getSender(MessageEntity entity) {
        return entity.getGroup()!=null? String.format(entity.getGroup().getName()+"::"+entity.getSender().getUserName()):entity.getSender().getUserName();
    }
    private String retrieveUsername(String key) {
         if(key.contains("::"))
             return key.substring(key.indexOf("::")+1);
         else
             return key;
    }

    private String retrieveGroupName(String key) {
        if(key.contains("::"))
            return key.substring(0,key.indexOf("::"));
        else
            return null;
    }

private boolean checkForBlockedConversation(UserEntity user1, UserEntity user2) {
        BlockedConversationEntityId id1= new BlockedConversationEntityId();
        BlockedConversationEntityId id2= new BlockedConversationEntityId();
        id1.setUser1(user1);
        id1.setUser2(user2);
        id2.setUser1(user2);
        id2.setUser2(user1);
        List<BlockedConversationEntityId> ids = new ArrayList<>();
        ids.add(id1);
        ids.add(id2);
         List<BlockedConversationEntity> entities = (List<BlockedConversationEntity>) blockedConversationRepository.findAllById(ids);
         if(CollectionUtils.isEmpty(entities))
             return false;
         return true;
}
}