package com.sparta.domain.insturctor.service;

import com.sparta.domain.insturctor.dto.InstructorRequestDto;
import com.sparta.domain.insturctor.dto.InstructorResponseDto;
import com.sparta.domain.insturctor.entity.Instructor;
import com.sparta.domain.insturctor.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    // 1. 강사 등록
    @Transactional
    public InstructorResponseDto createInstructor(InstructorRequestDto requestDto) {
        Instructor instructor = new Instructor(requestDto);
        instructorRepository.save(instructor);
        return new InstructorResponseDto(instructor);
    }

    // 2. 선택한 강사 정보 수정
    @Transactional
    public InstructorResponseDto updateInstructor(Long instructorId, InstructorRequestDto requestDto) {
        Instructor instructor = findById(instructorId);
        instructor.update(requestDto);
        instructorRepository.save(instructor);
        return new InstructorResponseDto(instructor);
    }

    // 3. 선택한 강사 정보 조회
    public InstructorResponseDto findInstructor(Long instructorId) {
        Instructor instructor = findById(instructorId);
        return new InstructorResponseDto(instructor);
    }

    // 0. 강사 조회
    private Instructor findById(Long instructorId) {
        return instructorRepository.findInstructorByInstructorId(instructorId).orElseThrow(() ->
                new IllegalArgumentException("등록된 강사가 정보가 없습니다."));
    }
}