package org.riomoney.service;

import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessages;

public interface UserMessageService {

    UserMessages getUserMessages(String to, String from);
    TextMessageResponse sendMessage(TextMessageObject textMessageObject);
}
