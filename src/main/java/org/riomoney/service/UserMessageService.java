package org.riomoney.service;

import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.exceptions.UserNotFoundException;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessages;

public interface UserMessageService {

    UserMessages getUserMessages(String to, String from) throws UserNotFoundException;
    TextMessageResponse sendMessage(String authorization,TextMessageObject textMessageObject) throws Exception;
}
