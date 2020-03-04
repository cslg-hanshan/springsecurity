package com.h2sj.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "db_permission")
@Setter
@Getter
public class Permission implements Serializable {
    private static final long serialVersionUID = 4356985969592663388L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long pId;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "p_description")
    private String pDescription;

    @Column(name = "p_url")
    private String pUrl;

    @OneToMany(targetEntity = RoleToPermission.class,mappedBy = "permission")
    private List<RoleToPermission> roleToPermissions = new ArrayList<>();
}
