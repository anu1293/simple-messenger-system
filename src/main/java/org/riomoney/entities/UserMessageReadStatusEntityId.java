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
public class UserMessageReadStatusEntityId  implements Serializable {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="message_id")
    private MessageEntity message ;
}
