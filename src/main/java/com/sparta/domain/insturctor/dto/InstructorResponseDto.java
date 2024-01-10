package com.sparta.domain.insturctor.dto;

import com.sparta.domain.insturctor.entity.Instructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponseDto {
    private Long instructorId;
    private String name;
    private int yearsOfExperience;
    private String company;
    private String phoneNumber;
    private String introduction;

    public InstructorResponseDto(Instructor saveInstructor) {
        this.instructorId = saveInstructor.getInstructorId();
        this.name = saveInstructor.getName();
        this.yearsOfExperience = saveInstructor.getYearsOfExperience();
        this.company = saveInstructor.getCompany();
        this.phoneNumber = saveInstructor.getPhoneNumber();
        this.introduction = saveInstructor.getIntroduction();
    }
}