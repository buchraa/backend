package com.mouridiyya.bibliomouride.repository;


import com.mouridiyya.bibliomouride.entity.security.ERole;
import com.mouridiyya.bibliomouride.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
