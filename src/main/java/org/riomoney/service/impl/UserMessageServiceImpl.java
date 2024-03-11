package org.riomoney.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.riomoney.entities.*;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessage;
import org.riomoney.model.UserMessages;
import org.riomoney.repositories.MessageRepository;
import org.riomoney.repositories.UserMessageRepository;
import org.riomoney.repositories.UserRepository;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    UserMessageRepository userMessageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;

    @Override
    public UserMessages getUserMessages(String to, String from) {

        List<UserMessageReadStatusEntity> userMessageReadStatusEntity;

        if(StringUtils.isNotBlank(from)) {
            userMessageReadStatusEntity =  userMessageRepository.fetchUserUnreadMessages(to,from);
        } else {
            userMessageReadStatusEntity = userMessageRepository.fetchUserUnreadMessages(to);
        }
        return getUserMessagesFromEntity(userMessageReadStatusEntity);

    }

    @Override
    public TextMessageResponse sendMessage(TextMessageObject textMessageObject) {
        UserEntity to = userRepository.findById(textMessageObject.getTo()).get();
        UserEntity from = userRepository.findById(textMessageObject.getFrom()).get();
        MessageEntity message = new MessageEntity();
        message.setMessage(textMessageObject.getText());
        message.setSender(from);
        message = messageRepository.save(message);
        UserMessageReadStatusEntityId id = new UserMessageReadStatusEntityId();
        id.setUser(to);
        id.setMessage(message);
        UserMessageReadStatusEntity userMessageReadStatusEntity = new UserMessageReadStatusEntity();
        userMessageReadStatusEntity.setId(id);
        userMessageReadStatusEntity.setRead(false);
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


}