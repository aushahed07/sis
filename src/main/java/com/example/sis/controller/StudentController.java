package com.example.sis.controller;

import com.example.sis.dto.ApiResponse;
import com.example.sis.dto.StudentRequest;
import com.example.sis.dto.StudentResponse;
import com.example.sis.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
@Tag(
        name = "Student",
        description = "Student management APIs"
)
public class StudentController {

    private final StudentService studentService;

    @Operation(
            summary = "Greetings API",
            description = "Greetings API is used to check the API service is working."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Welcome to SIS."
            )
    })
    @GetMapping("/hello")
    public ResponseEntity<ApiResponse> grettings() {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("API service working perfectly")
                .build());
    }

    @Operation(
            summary = "Get student by ID",
            description = "Retrieves a student by student's unique ID."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Student found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<StudentResponse>> getById(
            @Parameter(
                    description = "Unique ID of the student",
                    required = true,
                    example = "1"
            ) @PathVariable Long id) {
        StudentResponse studentResponse = studentService.getById(id);
        return ResponseEntity.ok(
                ApiResponse.<StudentResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Student retrieved successfully.")
                        .data(studentResponse)
                        .build());
    }

    @Operation(
            summary = "Get all students list",
            description = "Retrieves the list of all students."
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAll() {
        List<StudentResponse> students = studentService.getAll();
        return ResponseEntity.ok(
                ApiResponse.<List<StudentResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Student list retrieved successfully.")
                        .data(students)
                        .build());
    }

    @Operation(
            summary = "Create student",
            description = "Creates a new student."
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<StudentResponse>> create(@RequestBody StudentRequest request) {
        StudentResponse student = studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<StudentResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Student record created successfully.")
                        .data(student)
                        .build());
    }

    @Operation(
            summary = "Update student by ID",
            description = "Update an existing student information by ID."
    )
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<StudentResponse>> update(
            @Parameter(
                    description = "Unique ID of the student",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id, @RequestBody StudentRequest request) {
        StudentResponse response = studentService.update(id, request);
        return ResponseEntity.ok(
                ApiResponse.<StudentResponse>builder()
                    .status(HttpStatus.OK.value())
                    .message("Student record updated successfully.")
                    .data(response)
                    .build());
    }

    @Operation(
            summary = "Delete student",
            description = "Delete a student record by ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @Parameter(
                    description = "Unique ID of the student",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                    .status(HttpStatus.OK.value())
                    .message("Student record deleted successfully.")
                    .build());
    }
}
