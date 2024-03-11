package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "read_status")
public class UserMessageReadStatusEntity {

    @EmbeddedId
    private UserMessageReadStatusEntityId id;

    @Column(name = "is_read")
    private boolean isRead;
}
