package com.sparta.domain.insturctor.controller;

import com.sparta.domain.admin.entity.AdminRoleEnum;
import com.sparta.domain.insturctor.dto.InstructorRequestDto;
import com.sparta.domain.insturctor.dto.InstructorResponseDto;
import com.sparta.domain.insturctor.service.InstructorService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")

public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // 1. 강사 등록
    @PostMapping("/instructor")
    public InstructorResponseDto createInstructor(@RequestBody InstructorRequestDto requestDto) {
        return instructorService.createInstructor(requestDto);
    }

    // 2. 선택한 강사 정보 수정
    @Secured(AdminRoleEnum.Authority.MANAGER)
    @PutMapping("/instructor/{instructorId}")
    public InstructorResponseDto updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorRequestDto requestDto) {
        return instructorService.updateInstructor(instructorId, requestDto);
    }

    // 3. 선택한 강사 정보 조회
    @GetMapping("/instructor/{instructorId}")
    public InstructorResponseDto findInstructor(@PathVariable Long instructorId) {
        return instructorService.findInstructor(instructorId);
    }
}