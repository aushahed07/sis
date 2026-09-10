package com.example.sis.mapper;

import com.example.sis.dto.StudentRequest;
import com.example.sis.dto.StudentResponse;
import com.example.sis.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .mobileNumber(request.getMobileNumber())
                .age(request.getAge())
                .build();
    }

    public StudentResponse toResponse(Student entity) {
        return StudentResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .emailAddress(entity.getEmailAddress())
                .mobileNumber(entity.getMobileNumber())
                .age(entity.getAge())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
