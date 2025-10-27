package com.example.learn.controller;

import com.example.learn.exception.ResourceNotFoundException;
import com.example.learn.model.Salary;
import com.example.learn.repository.SalaryRepository;
import com.example.learn.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/{id}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long id)
    {
        Salary salary = salaryService
                .getSalaryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no salary record found"));
        return ResponseEntity.ok(salary);
    }
}
