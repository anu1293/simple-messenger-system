package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ToString
@Data
@Entity
@Table(name = "users_messages")
public class UserMessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="to_user")
    private UserEntity to;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="from_user")
    private UserEntity from;

    @Column(name = "message")
    private String message;

    @Column(name = "message_timestamp")
    private Timestamp timestamp;

    @Column(name = "is_read")
    private boolean isRead;
}
