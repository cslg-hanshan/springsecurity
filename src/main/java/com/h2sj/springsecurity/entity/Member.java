package com.h2sj.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_member")
@Setter
@Getter
public class Member implements Serializable {
    private static final long serialVersionUID = 5784951730010061908L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long mId;

    @Column(name = "m_username")
    private String mUsername;

    @Column(name = "m_password")
    private String mPassword;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "r_id",referencedColumnName = "r_id")
    private Role role;
}
