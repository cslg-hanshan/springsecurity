package com.h2sj.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "db_role_to_permission")
@Setter
@Getter
public class RoleToPermission implements Serializable {
    private static final long serialVersionUID = -37319463935772562L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rtp_id")
    private Long rtpId;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "r_id",referencedColumnName = "r_id")
    private Role role;

    @ManyToOne(targetEntity = Permission.class)
    @JoinColumn(name = "p_id",referencedColumnName = "p_id")
    private Permission permission;
}
