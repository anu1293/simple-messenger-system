package org.riomoney.service.impl;

import jakarta.persistence.DiscriminatorValue;
import org.apache.commons.lang3.StringUtils;
import org.riomoney.entities.UserMessagesEntity;
import org.riomoney.entities.UserMessagesReadInfoEntity;
import org.riomoney.model.UserMessage;
import org.riomoney.model.UserMessages;
import org.riomoney.repositories.UserMessageRepository;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    UserMessageRepository userMessageRepository;

    @Override
    public UserMessages getUserMessages(String to, String from) {

        List<UserMessagesEntity> userMessagesEntity;
        List<UserMessagesReadInfoEntity> userMessagesReadInfoEntity = null;

        if(StringUtils.isNotBlank(from)) {
            userMessagesReadInfoEntity =  userMessageRepository.fetchUserUnreadMessages(to,from);
        } else {
            userMessagesReadInfoEntity = userMessageRepository.fetchUserUnreadMessages(to);
        }
return getUserMessagesFromEntity(userMessagesReadInfoEntity);
     //   return getUserMessagesFromEntity(userMessagesEntity);

    }


    private UserMessages getUserMessagesFromEntity(List<UserMessagesReadInfoEntity> userMessagesReadInfoEntity) {
        UserMessages userMessages = new UserMessages();
        List<UserMessage> data = userMessagesReadInfoEntity
        .stream()
        .filter(msg -> msg.getClass().getAnnotation(DiscriminatorValue.class).value().equals("DIRECT"))
        .collect(Collectors.groupingBy(msg -> msg.getChat().getFromUserId().getUserName(),Collectors.mapping(msg->msg.getMessage().getMessage(), Collectors.toList())
        )).entrySet().stream().map(entry -> new UserMessage().username(entry.getKey()).texts(entry.getValue()))
        .collect(Collectors.toList());
        userMessages.setStatus("SUCCESS");
        userMessages.setMessage(CollectionUtils.isEmpty(data)?"no new message available":"new messages available");
        userMessages.setData(data);
        return userMessages;
    }

    private UserMessages getUserMessagesFromEntity1(List<UserMessagesEntity> userMessagesEntity) {
    UserMessages userMessages = new UserMessages();
        List<UserMessage> data = userMessagesEntity.stream()
            .collect(Collectors.groupingBy(
                    msg -> msg.getFrom().getUserName(),
                    Collectors.mapping(UserMessagesEntity::getMessage, Collectors.toList())
            )).entrySet().stream().map(entry -> new UserMessage().username(entry.getKey()).texts(entry.getValue()))
            .collect(Collectors.toList());

    userMessages.setStatus("SUCCESS");
    userMessages.setMessage(CollectionUtils.isEmpty(data)?"no new message available":"new messages available");
    userMessages.setData(data);
    return userMessages;
}


}