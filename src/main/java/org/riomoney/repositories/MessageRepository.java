package org.riomoney.repositories;

import org.riomoney.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity,Integer> {
}
