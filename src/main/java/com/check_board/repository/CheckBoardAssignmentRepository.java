package com.check_board.repository;

import com.check_board.entity.CheckBoardAssignmentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckBoardAssignmentRepository extends JpaRepository<CheckBoardAssignmentEntity, Integer>{
    public Optional<CheckBoardAssignmentEntity> findById(int id);
}
