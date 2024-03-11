package org.riomoney.repositories;

import org.riomoney.entities.GroupEntity;
import org.riomoney.entities.UserEntity;
import org.riomoney.entities.UserGroupEntity;
import org.riomoney.entities.UserGroupId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserGroupsRepository extends CrudRepository<UserGroupEntity, UserGroupId> {
    @Query("select UserEntity from UserGroupEntity where id.group=?1")
    List<UserEntity> findUsersByGroupId(GroupEntity group);
}
