package com.example.learn.repository;

import com.example.learn.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
