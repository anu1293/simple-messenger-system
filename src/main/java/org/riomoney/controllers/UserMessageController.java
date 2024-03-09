package org.riomoney.controllers;

import org.apache.coyote.Response;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessages;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UserMessageController implements MessagesApi{
    @Autowired
    UserMessageService userMessageService;
    @Override
    public ResponseEntity<UserMessages> fetchUserMessages(String username, String friend) {
        return ResponseEntity.ok(userMessageService.getUserMessages(username,friend));
    }

    @Override
    public ResponseEntity<TextMessageResponse> sendMessageToUser(String username, TextMessageObject textMessageObject) {
        return null;
    }
}
