package com.check_board.repository;

import com.check_board.entity.DivisionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository<DivisionEntity, Integer>{
    public Optional<DivisionEntity> findById(int id);
    public Optional<DivisionEntity> findByName(String name);
}
