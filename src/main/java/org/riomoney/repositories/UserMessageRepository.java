package org.riomoney.repositories;

import org.riomoney.entities.UserEntity;
import org.riomoney.entities.UserMessageReadStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessageReadStatusEntity,Integer> {

    @Query("SELECT m FROM UserMessageReadStatusEntity m where m.id.user=?1 and m.isRead=FALSE order by m.id.message.timestamp asc")
    List<UserMessageReadStatusEntity> fetchUserUnreadMessages(UserEntity to);

    @Query("SELECT m FROM UserMessageReadStatusEntity m WHERE m.id.user=?1 and m.id.message.sender=?2 and m.isRead=FALSE order by m.id.message.timestamp asc")
    List<UserMessageReadStatusEntity> fetchUserUnreadMessages(UserEntity to, UserEntity from);
}
