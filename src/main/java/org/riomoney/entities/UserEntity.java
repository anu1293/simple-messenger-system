package org.riomoney.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
}
