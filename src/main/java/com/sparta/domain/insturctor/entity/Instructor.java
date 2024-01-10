package com.sparta.domain.insturctor.entity;

import com.sparta.domain.insturctor.dto.InstructorRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;
    private String name;
    private int yearsOfExperience;
    private String company;
    private String phoneNumber;
    private String introduction;

    public Instructor(InstructorRequestDto requestDto) {
        this.name = requestDto.getName();
        this.yearsOfExperience = requestDto.getYearsOfExperience();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduction = requestDto.getIntroduction();
    }

    public void update(InstructorRequestDto requestDto) {
        this.yearsOfExperience = requestDto.getYearsOfExperience();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduction = requestDto.getIntroduction();
    }
}