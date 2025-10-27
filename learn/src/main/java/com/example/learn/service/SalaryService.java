package com.example.learn.service;

import com.example.learn.model.Salary;
import com.example.learn.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
     public Optional<Salary> getSalaryById(@PathVariable Long id)
     {
         return salaryRepository.findById(id);
     }
}
