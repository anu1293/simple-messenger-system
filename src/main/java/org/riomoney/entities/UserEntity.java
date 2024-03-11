package org.riomoney.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username",unique = true)
    private String userName;

    @Column(name = "password")
    private String password;
}
