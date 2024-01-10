package com.sparta.domain.lecture.repository;

import com.sparta.domain.insturctor.entity.Instructor;
import com.sparta.domain.lecture.entity.CategoryEnum;
import com.sparta.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findLectureByLectureId(Long lectureId);

    List<Lecture> findAllByInstructorOrderByLectureIdDesc(Instructor instructor);

    List<Lecture> findAllByCategoryOrderByLectureIdDesc(CategoryEnum category);
}