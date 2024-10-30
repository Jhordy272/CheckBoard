package com.check_board.repository;

import com.check_board.entity.ServiceLineEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceLineRepository extends JpaRepository<ServiceLineEntity, Integer>{
    public Optional<ServiceLineEntity> findById(int id);
    public Optional<ServiceLineEntity> findByName(String name);
}
