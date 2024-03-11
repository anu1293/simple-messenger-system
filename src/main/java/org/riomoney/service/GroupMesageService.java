package org.riomoney.service;

import org.riomoney.model.TextMessageObject;
import org.riomoney.model.TextMessageResponse;

public interface GroupMesageService {
    TextMessageResponse sendMessage(TextMessageObject textMessageObject);
}
