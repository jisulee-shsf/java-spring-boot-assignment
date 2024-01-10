package com.sparta.domain.lecture.dto;

import com.sparta.domain.lecture.entity.CategoryEnum;
import lombok.Getter;

@Getter
public class LectureRequestDto {
    private String lectureTitle;
    private Long price;
    private String description;
    private CategoryEnum category;
    private String instructorName;
}