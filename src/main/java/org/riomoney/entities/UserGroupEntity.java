package org.riomoney.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "user_groups")
@EqualsAndHashCode
public class UserGroupEntity {

    @EmbeddedId
    private UserGroupId id;
}
