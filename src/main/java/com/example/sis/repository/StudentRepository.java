package com.example.sis.repository;

import com.example.sis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);

    List<Student> findAll();

    boolean existsByEmailAddress(String emailAddress);
}
