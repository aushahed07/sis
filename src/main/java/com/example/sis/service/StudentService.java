package com.example.sis.service;

import com.example.sis.dto.StudentRequest;
import com.example.sis.dto.StudentResponse;
import com.example.sis.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    StudentResponse create(StudentRequest request);

    StudentResponse update(Long id, StudentRequest request) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    StudentResponse getById(Long id) throws ResourceNotFoundException;

    List<StudentResponse> getAll();
}
