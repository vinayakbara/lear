package com.example.learn.repository;

import com.example.learn.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
