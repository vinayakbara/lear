package com.example.learn.controller;

import com.example.learn.exception.ResourceNotFoundException;
import com.example.learn.model.Department;
import com.example.learn.service.DepartmentService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;


import java.util.List;



@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id)
    {
        Department department = departmentService
                .getDepartmentById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department not found")
                );
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department)
    {
        return departmentService.saveDepartment(department);
    }

    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody Department departmentDetails, @PathVariable Long id)
    {
        Department department = departmentService
                .getDepartmentById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department not found")
                );
        department.setName(departmentDetails.getName());
        Department updatedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.ok(updatedDepartment);
    }
}
