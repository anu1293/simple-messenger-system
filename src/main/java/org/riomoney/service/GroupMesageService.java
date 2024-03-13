package org.riomoney.service;

import org.riomoney.exceptions.GroupNotFoundException;
import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;

public interface GroupMesageService {
    TextMessageResponse sendMessage(String authorization,TextMessageObject textMessageObject) throws GroupNotFoundException;
}
