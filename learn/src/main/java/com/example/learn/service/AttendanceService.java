package com.example.learn.service;

import com.example.learn.model.Attendance;
import com.example.learn.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Optional<Attendance> getAttendanceById(@PathVariable Long id)
    {
        return attendanceRepository.findById(id);
    }
}
