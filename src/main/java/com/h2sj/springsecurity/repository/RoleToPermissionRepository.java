package com.h2sj.springsecurity.repository;

import com.h2sj.springsecurity.entity.RoleToPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleToPermissionRepository extends JpaRepository<RoleToPermission,Long>, JpaSpecificationExecutor<RoleToPermission> {
}
