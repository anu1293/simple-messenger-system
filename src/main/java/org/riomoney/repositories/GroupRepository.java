package org.riomoney.repositories;

import org.riomoney.entities.GroupEntity;
import org.riomoney.entities.UserEntity;
import org.riomoney.entities.UserMessageReadStatusEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity,Integer> {

    GroupEntity findById(int id);
    GroupEntity findByName(String name);
}
