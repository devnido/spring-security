package com.example.security.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.security.persistence.entity.RoleEntity;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

  List<RoleEntity> findRoleEntitiesByRoleIn(List<String> roleNames);

}
