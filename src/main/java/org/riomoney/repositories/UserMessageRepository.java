package org.riomoney.repositories;

import org.riomoney.entities.UserMessagesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserMessageRepository extends CrudRepository<UserMessagesEntity,Integer> {

    @Query("SELECT * FROM UserMessages WHERE to_user=?1 and isRead=FALSE order by message_timestamp asc")
    List<UserMessagesEntity> fetchUserUnreadMessages(String to);

    @Query("SELECT * FROM UserMessages WHERE to_user=?1 and from_user=?2 and isRead=FALSE order by message_timestamp asc")
    List<UserMessagesEntity> fetchUserUnreadMessages(String to, String from);
}
