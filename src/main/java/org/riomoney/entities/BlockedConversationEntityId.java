package org.riomoney.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@Embeddable
@EqualsAndHashCode
public class BlockedConversationEntityId implements Serializable {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id1")
    private UserEntity user1;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id2")
    private UserEntity user2;
}

