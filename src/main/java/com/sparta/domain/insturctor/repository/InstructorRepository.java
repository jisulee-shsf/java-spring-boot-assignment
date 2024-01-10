package com.sparta.domain.insturctor.repository;

import com.sparta.domain.insturctor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByName(String name);
    Optional<Instructor> findInstructorByInstructorId(Long instructorId);
}