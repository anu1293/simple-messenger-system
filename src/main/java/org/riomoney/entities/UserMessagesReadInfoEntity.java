package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Data
@Entity
@Table(name = "user_messages_read_info")
public class UserMessagesReadInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="chat_id")
    private ChatEntity chat;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="message_id")
    private MessageEntity message ;

    @Column(name = "is_read")
    private boolean isRead;
}
