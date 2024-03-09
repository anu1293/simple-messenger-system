package org.riomoney.repositories;

import org.riomoney.entities.UserMessagesEntity;
import org.riomoney.entities.UserMessagesReadInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessagesReadInfoEntity,Integer> {

    @Query("SELECT m FROM UserMessagesReadInfoEntity m where m.user.userName=?1 and m.isRead=FALSE order by m.message.timestamp asc")
    List<UserMessagesReadInfoEntity> fetchUserUnreadMessages(String to);

    @Query("SELECT m FROM UserMessagesReadInfoEntity m WHERE m.user.userName=?1 and m.chat.fromUserId.userName=?2 and m.isRead=FALSE order by m.message.timestamp asc")
    List<UserMessagesReadInfoEntity> fetchUserUnreadMessages(String to, String from);
}
