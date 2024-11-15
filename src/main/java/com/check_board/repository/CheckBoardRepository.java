package com.check_board.repository;

import com.check_board.entity.CheckBoardEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckBoardRepository extends JpaRepository<CheckBoardEntity, Integer>{
    public Optional<CheckBoardEntity> findById(int id);
    public Optional<CheckBoardEntity> findByName(String name);
}
