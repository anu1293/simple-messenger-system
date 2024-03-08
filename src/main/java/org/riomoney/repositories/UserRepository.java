package org.riomoney.repositories;

import org.riomoney.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUserName(String userName);

    @Query("select userName from User")
    List<String> listUserNames();
}
