package org.riomoney.repositories;

import org.riomoney.entities.UserMessageReadStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessageReadStatusEntity,Integer> {

    @Query("SELECT m FROM UserMessageReadStatusEntity m where m.id.user.id=?1 and m.isRead=FALSE order by m.id.message.timestamp asc")
    List<UserMessageReadStatusEntity> fetchUserUnreadMessages(String to);

    @Query("SELECT m FROM UserMessagesReadInfoEntity m WHERE m.id.user.id=?1 and m.id.message.sender.id=?2 and m.isRead=FALSE order by m.id.message.timestamp asc")
    List<UserMessageReadStatusEntity> fetchUserUnreadMessages(String to, String from);
}
