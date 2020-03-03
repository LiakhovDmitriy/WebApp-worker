package com.example.test_task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkerRepository extends JpaRepository <Worker, Long> {

    @Query("SELECT c FROM Worker c WHERE c.position = :position")
    List<Worker> findByPosition(@Param("position") Position position, Pageable pageable);

    @Query("SELECT COUNT (c) FROM Worker c WHERE  c.position = :position")
    long countByPosition(@Param("position") Position position);

    @Query("SELECT c FROM Worker c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Worker> findByPattern(@Param("pattern") String pattern, org.springframework.data.domain.Pageable pageable);

}
