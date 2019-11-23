package com.tsystems.transportinfo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="auth_user_group")
@NoArgsConstructor
@Getter
@Setter
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="auth_group")
    private String authGroup;

    public AuthGroup(String username, String authGroup) {
        this.username = username;
        this.authGroup = authGroup;
    }

}
