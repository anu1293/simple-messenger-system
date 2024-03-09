package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "chat")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="chat_type",
        discriminatorType = DiscriminatorType.STRING)
public class ChatEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="to_user")
    private UserEntity toUserId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="from_user")
    private UserEntity fromUserId;
}
