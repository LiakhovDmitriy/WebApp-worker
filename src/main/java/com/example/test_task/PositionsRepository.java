package com.example.test_task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository <Position, Long> {
}
