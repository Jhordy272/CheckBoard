package com.check_board.repository;

import com.check_board.entity.CheckBoardEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckBoardRepository extends JpaRepository<CheckBoardEntity, Integer>{
    public Optional<CheckBoardEntity> findById(int id);
    public Optional<CheckBoardEntity> findByName(String name);
    @Query("SELECT e FROM CheckBoardEntity e WHERE e.project.id = :projectId")
    List<CheckBoardEntity> findByProject(@Param("projectId") int projectId);
}
