package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
@ToString
@Data
@Embeddable
@EqualsAndHashCode
public class UserGroupId implements Serializable {

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="group_id")
    private GroupEntity group;
}
