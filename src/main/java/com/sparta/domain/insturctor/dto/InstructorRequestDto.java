package com.sparta.domain.insturctor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class InstructorRequestDto {
    private String name;
    private int yearsOfExperience;
    private String company;
    private String phoneNumber;
    private String introduction;
}