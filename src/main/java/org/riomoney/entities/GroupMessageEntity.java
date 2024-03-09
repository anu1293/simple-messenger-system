package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
@DiscriminatorValue("GROUP")
public class GroupMessageEntity  extends ChatEntity {
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="group_id")
    private GroupEntity group;
}
