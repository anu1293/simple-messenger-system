package org.riomoney.service;

import org.riomoney.model.UserMessage;
import org.riomoney.model.UserMessages;

public interface UserMessageService {

    public UserMessages getUserMessages(String to, String from);
}
