package com.sparta.domain.lecture.controller;

import com.sparta.domain.admin.entity.AdminRoleEnum;
import com.sparta.domain.lecture.dto.LectureRequestDto;
import com.sparta.domain.lecture.dto.LectureResponseDto;
import com.sparta.domain.lecture.entity.CategoryEnum;
import com.sparta.domain.lecture.service.LectureService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    // 1. 강의 등록
    @PostMapping("/lecture")
    public LectureResponseDto createLecture(@RequestBody LectureRequestDto requestDto) {
        return lectureService.createLecture(requestDto);
    }

    // 2. 선택한 강의 정보 수정
    @Secured(AdminRoleEnum.Authority.MANAGER)
    @PutMapping("/lecture/{lectureId}")
    public LectureResponseDto updateLecture(@PathVariable Long lectureId, @RequestBody LectureRequestDto requestDto) {
        return lectureService.updateLecture(lectureId, requestDto);
    }

    // 3. 선택한 강의 정보 조회
    @GetMapping("/lecture/{lectureId}")
    public LectureResponseDto readLecture(@PathVariable Long lectureId) {
        return lectureService.readLecture(lectureId);
    }

    // 4. 선택한 강사별 강의 정보 조회
    @GetMapping("/lecture/instructor/{instructorId}")
    public List<LectureResponseDto> readLectureByInstructor(@PathVariable Long instructorId) {
        return lectureService.readLectureByInstructor(instructorId);
    }

    // 5. 선택한 카테고리별 강의 정보 조회
    @GetMapping("/lecture/category/{category}")
    public List<LectureResponseDto> readLectureByCategory(@PathVariable CategoryEnum category) {
        return lectureService.readLectureByCategory(category);
    }
}