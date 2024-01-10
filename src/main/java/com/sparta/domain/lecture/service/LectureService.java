package com.sparta.domain.lecture.service;

import com.sparta.domain.insturctor.entity.Instructor;
import com.sparta.domain.insturctor.repository.InstructorRepository;
import com.sparta.domain.lecture.dto.LectureRequestDto;
import com.sparta.domain.lecture.dto.LectureResponseDto;
import com.sparta.domain.lecture.entity.CategoryEnum;
import com.sparta.domain.lecture.entity.Lecture;
import com.sparta.domain.lecture.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    public LectureService(LectureRepository lectureRepository, InstructorRepository instructorRepository) {
        this.lectureRepository = lectureRepository;
        this.instructorRepository = instructorRepository;
    }

    // 1. 강의 등록
    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto requestDto) {
        Instructor instructor = instructorRepository.findByName(requestDto.getInstructorName()).orElseThrow(() ->
                new IllegalArgumentException("등록된 강사 정보가 없습니다."));
        Lecture lecture = new Lecture(requestDto, instructor);
        lectureRepository.save(lecture);
        return new LectureResponseDto(lecture);
    }

    // 2. 선택한 강의 정보 수정
    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto) {
        Lecture lecture = findById(lectureId);
        lecture.update(requestDto);
        lectureRepository.save(lecture);
        return new LectureResponseDto(lecture);
    }

    // 3. 선택한 강의 정보 조회
    public LectureResponseDto readLecture(Long lectureId) {
        Lecture lecture = findById(lectureId);
        return new LectureResponseDto(lecture);
    }

    // 4. 선택한 강사별 강의 정보 조회
    public List<LectureResponseDto> readLectureByInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() ->
                new IllegalArgumentException("등록된 강사 정보가 없습니다."));
        return lectureRepository.findAllByInstructorOrderByLectureIdDesc(instructor)
                .stream().map(LectureResponseDto::new).toList();
    }

    // 5. 선택한 카테고리별 강의 정보 조회
    public List<LectureResponseDto> readLectureByCategory(CategoryEnum category) {
        return lectureRepository.findAllByCategoryOrderByLectureIdDesc(category)
                .stream().map(LectureResponseDto::new).toList();
    }

    public Lecture findById(Long lectureId) {
        return lectureRepository.findLectureByLectureId(lectureId).orElseThrow(() ->
                new IllegalArgumentException("등록된 강의 정보가 없습니다."));
    }
}