package com.check_board.security.repository;

import com.check_board.security.entity.RolEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity, Integer>{
    public Optional<RolEntity> findById(int id);
    public Optional<RolEntity> findByName(String name);
}
