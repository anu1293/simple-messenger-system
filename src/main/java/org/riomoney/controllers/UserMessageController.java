package org.riomoney.controllers;

import org.riomoney.exceptions.BlockedConversationException;
import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.*;
import org.riomoney.service.GroupMesageService;
import org.riomoney.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMessageController implements MessagesApi{
    @Autowired
    UserMessageService userMessageService;
    @Autowired
    GroupMesageService groupMesageService;
    @Override
    public ResponseEntity<UserMessages> fetchUserMessages(String authorization, String friend) {
        UserMessages response = new UserMessages();
        try{
           return ResponseEntity.ok(userMessageService.getUserMessages(authorization,friend));
       } catch(UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("USER_NOT_FOUND").message("ONE OR MORE INVALID USERS"));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.status("SERVER ERROR").message("something went wrong"));
        }
    }

    @Override
    public ResponseEntity<TextMessageResponse> sendMessageToUser(String authorization, MessageType messageType, TextMessageObject textMessageObject) {
        TextMessageResponse response = new TextMessageResponse();
        try{
            return switch (messageType) {
                case DIRECT -> ResponseEntity.ok(userMessageService.sendMessage(authorization,textMessageObject));
                case GROUP -> ResponseEntity.ok(groupMesageService.sendMessage(authorization,textMessageObject));
            };
        } catch(UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("USER_NOT_FOUND").text("ONE OR MORE INVALID USERS"));
        } catch(GroupNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("GROUP_NOT_FOUND").text("INVALID GROUP ID"));
        } catch(BlockedConversationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.status("USER BLOCKED").text("EITHER OF THE USERS HAVE BLOCKED THE OTHER"));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.status("SERVER ERROR").text("something went wrong"));
        }
    }
}

