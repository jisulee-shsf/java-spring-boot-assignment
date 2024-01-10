package com.sparta.domain.insturctor.entity;

import com.sparta.domain.insturctor.dto.InstructorRequestDto;
//import com.sparta.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

//    @OneToMany(mappedBy = "instructor")
//    private List<Lecture> lectureList = new ArrayList<>();

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