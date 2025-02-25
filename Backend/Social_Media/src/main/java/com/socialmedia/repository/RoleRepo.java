package com.socialmedia.repository;

import com.socialmedia.modal.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    List<Role> findByDefaultRole(Boolean defaultRole);
}
