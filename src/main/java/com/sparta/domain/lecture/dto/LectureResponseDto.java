package com.sparta.domain.lecture.dto;

import com.sparta.domain.lecture.entity.CategoryEnum;
import com.sparta.domain.lecture.entity.Lecture;
import lombok.Getter;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private String lectureTitle;
    private Long price;
    private String description;
    private CategoryEnum category;
    private String instructorName;
//    private LocalDateTime registrationDate;

    public LectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureTitle = lecture.getLectureTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.instructorName = lecture.getInstructor().getName();
//        this.registrationDate = lecture.getRegistrationDate();
    }
}