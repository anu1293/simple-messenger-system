package org.riomoney.service.impl;

import jakarta.persistence.DiscriminatorValue;
import org.apache.commons.lang3.StringUtils;
import org.riomoney.entities.ChatEntity;
import org.riomoney.entities.GroupMessageEntity;
import org.riomoney.entities.UserMessagesEntity;
import org.riomoney.entities.UserMessagesReadInfoEntity;
import org.riomoney.model.UserMessage;
import org.riomoney.model.UserMessages;
import org.riomoney.repositories.UserMessageRepository;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
@Service
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

    }
    private UserMessages getUserMessagesFromEntity(List<UserMessagesReadInfoEntity> userMessagesReadInfoEntity) {
        UserMessages userMessages = new UserMessages();
        List<UserMessage> data = userMessagesReadInfoEntity
        .stream()
        .collect(Collectors.groupingBy(UserMessagesReadInfoEntity::getChat,Collectors.mapping(msg->msg.getMessage().getMessage(), Collectors.toList())
        )).entrySet().stream().map(entry -> new UserMessage().username(entry.getKey().getFromUserId().getUserName()).texts(entry.getValue()).groupName(getGroupName(entry.getKey())))
        .collect(Collectors.toList());

        userMessages.setStatus("SUCCESS");
        userMessages.setMessage(CollectionUtils.isEmpty(data)?"no new message available":"new messages available");
        userMessages.setData(data);
        return userMessages;
    }

    private String getGroupName(ChatEntity entity) {
        if(entity instanceof GroupMessageEntity) {
            return ((GroupMessageEntity) entity).getGroup().getName();
        }
        return null;
    }
}