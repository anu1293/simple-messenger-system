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
@Table(name = "blocked_conversation")
@EqualsAndHashCode
public class BlockedConversationEntity {
    @EmbeddedId
    private BlockedConversationEntityId id;
}
