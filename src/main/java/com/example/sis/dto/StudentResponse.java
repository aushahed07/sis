package com.example.sis.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private Integer age;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
