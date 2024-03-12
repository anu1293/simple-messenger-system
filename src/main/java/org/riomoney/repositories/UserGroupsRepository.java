package org.riomoney.repositories;

import org.riomoney.entities.GroupEntity;
import org.riomoney.entities.UserGroupEntity;
import org.riomoney.entities.UserGroupId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserGroupsRepository extends CrudRepository<UserGroupEntity, UserGroupId> {
    @Query("select users from UserGroupEntity users where id.group=?1")
    List<UserGroupEntity> findUsersByGroupId(GroupEntity group);
}
