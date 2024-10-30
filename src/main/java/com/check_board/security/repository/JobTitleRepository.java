package com.check_board.security.repository;

import com.check_board.security.entity.JobTitleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitleEntity, Integer>{
    public Optional<JobTitleEntity> findById(int id);
    public Optional<JobTitleEntity> findByName(String name);
}
