package com.example.learn.controller;

import com.example.learn.exception.ResourceNotFoundException;
import com.example.learn.model.Attendance;
import com.example.learn.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id)
    {
        Attendance attendance = attendanceService
                .getAttendanceById(id)
                .orElseThrow( () -> new ResourceNotFoundException("no attendance record found"));

        return ResponseEntity.ok(attendance);

    }
}
