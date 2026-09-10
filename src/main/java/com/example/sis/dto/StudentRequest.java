package com.example.sis.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotBlank(message = "First name is required")
    @Schema(
            description = "Student first name",
            example = "Ashraf Uddin"
    )
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Schema(
            description = "Student last name",
            example = "Shahed"
    )
    private String lastName;

    @NotBlank(message = "Email address is required")
    @Schema(
            description = "Student email address",
            example = "aushahed.dev@gmail.com"
    )
    private String emailAddress;


    private String mobileNumber;

    @NotNull(message = "Age is required")
    @Schema(
            description = "Student's age",
            example = "20"
    )
    private Integer age;
}
