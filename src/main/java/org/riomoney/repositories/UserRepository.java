package org.riomoney.repositories;

import org.riomoney.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUserName(String userName);

    UserEntity findById(int id);

    @Query("select userName from UserEntity")
    List<String> listUserNames();

    @Query("select m from UserEntity m where m.id in ?1")
    List<UserEntity> listUsersById(List<Integer> id);
}
