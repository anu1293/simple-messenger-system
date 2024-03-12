package org.riomoney.repositories;

import org.riomoney.entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity,Integer> {

    GroupEntity findById(int id);
    GroupEntity findByName(String name);
}
