package com.h2sj.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_role")
@Setter
@Getter
public class Role implements Serializable {
    private static final long serialVersionUID = 7070817489040534745L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long rId;

    @Column(name = "r_name")
    private String rName;

    @OneToMany(targetEntity = Member.class,mappedBy = "role")
    private List<Member> members = new ArrayList<>();

    @OneToMany(targetEntity = RoleToPermission.class,mappedBy = "role")
    private List<RoleToPermission> permissions = new ArrayList<>();
}
