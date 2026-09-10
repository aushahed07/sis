package com.example.sis.service.impl;

import com.example.sis.dto.StudentRequest;
import com.example.sis.dto.StudentResponse;
import com.example.sis.entity.Student;
import com.example.sis.exception.ResourceNotFoundException;
import com.example.sis.mapper.StudentMapper;
import com.example.sis.repository.StudentRepository;
import com.example.sis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentResponse create(StudentRequest request) {

        if (repository.existsByEmailAddress(request.getEmailAddress())) {
            throw new IllegalArgumentException("Email address already exists.");
        }

        Student student = repository.save(mapper.toEntity(request));

        return mapper.toResponse(student);
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) throws ResourceNotFoundException {
        Student student = repository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Student record not found with id : " + id));
        student = mapper.toEntity(request);
        return mapper.toResponse(repository.save(student));
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Student student = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Student not found with id : " + id)
                );
        repository.delete(student);
    }

    @Override
    public StudentResponse getById(Long id) {
        Student student = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Student not found with id : " + id));
        return mapper.toResponse(repository.getReferenceById(id));
    }

    @Override
    public List<StudentResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

}
