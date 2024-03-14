package org.riomoney.repositories;

import org.riomoney.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface BlockedConversationRepository extends CrudRepository<BlockedConversationEntity, BlockedConversationEntityId> {
    @Query("select e from BlockedConversationEntity e where e.id = ?1 or e.id=?2")
    List<BlockedConversationEntity> findUsersByGroupId(BlockedConversationEntityId id1, BlockedConversationEntityId id2);
}