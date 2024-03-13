package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "read_status")
@EqualsAndHashCode
public class UserMessageReadStatusEntity {

    @EmbeddedId
    private UserMessageReadStatusEntityId id;

    @Column(name = "is_read")
    private boolean isRead;
}
