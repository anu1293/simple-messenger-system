package org.riomoney.controllers;

import org.riomoney.model.MessageType;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessages;
import org.riomoney.service.GroupMesageService;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMessageController implements MessagesApi{
    @Autowired
    UserMessageService userMessageService;
    @Autowired
    GroupMesageService groupMesageService;
    @Override
    public ResponseEntity<UserMessages> fetchUserMessages(String username, String friend) {
        return ResponseEntity.ok(userMessageService.getUserMessages(username,friend));
    }

    @Override
    public ResponseEntity<TextMessageResponse> sendMessageToUser(MessageType messageType, TextMessageObject textMessageObject) {
        return switch (messageType) {
            case DIRECT -> ResponseEntity.ok(userMessageService.sendMessage(textMessageObject));
            case GROUP -> ResponseEntity.ok(groupMesageService.sendMessage(textMessageObject));
        };
    }
}
