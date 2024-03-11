package org.riomoney.service;

import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;
import org.riomoney.model.UserMessage;
import org.riomoney.model.UserMessages;

public interface UserMessageService {

    public UserMessages getUserMessages(String to, String from);
    public TextMessageResponse sendMessage(TextMessageObject textMessageObject);
}
