package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
@ToString
@Data
@Entity
@Table(name = "messages")
@RequiredArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "message_timestamp", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="sender_id")
    private UserEntity sender;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="group_id")
    private GroupEntity group;
}
