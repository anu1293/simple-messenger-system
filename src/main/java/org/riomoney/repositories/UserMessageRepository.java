package org.riomoney.repositories;

import org.riomoney.entities.UserMessagesEntity;
import org.riomoney.entities.UserMessagesReadInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserMessageRepository extends CrudRepository<UserMessagesReadInfoEntity,Integer> {

//    @Query("SELECT * FROM UserMessagesReadInfoEntity WHERE to_user=?1 and isRead=FALSE order by message_timestamp asc")
//    List<UserMessagesReadInfoEntity> fetchUserUnreadMessages1(String to);

    @Query("SELECT m FROM UserMessagesReadInfoEntity m where m.user.userName=?1 and m.isRead=FALSE order by m.message.timestamp asc")
    List<UserMessagesReadInfoEntity> fetchUserUnreadMessages(String to);

    @Query("SELECT m FROM UserMessagesReadInfoEntity m WHERE m.user.userName=?1 and m.chat.fromUserId.userName=?2 and m.isRead=FALSE order by m.message.timestamp asc")
    List<UserMessagesReadInfoEntity> fetchUserUnreadMessages(String to, String from);
}
