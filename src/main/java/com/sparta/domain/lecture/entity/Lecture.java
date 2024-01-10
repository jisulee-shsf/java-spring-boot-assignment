package com.sparta.domain.lecture.entity;

import com.sparta.domain.insturctor.entity.Instructor;
import com.sparta.domain.lecture.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Lecture { // extends Timestamped
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    private String lectureTitle;
    private Long price;
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Lecture(LectureRequestDto requestDto, Instructor instructor) {
        this.lectureTitle = requestDto.getLectureTitle();
        this.price = requestDto.getPrice();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
        this.instructor = instructor;
    }

    public void update(LectureRequestDto requestDto) {
        this.lectureTitle = requestDto.getLectureTitle();
        this.price = requestDto.getPrice();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
    }
}