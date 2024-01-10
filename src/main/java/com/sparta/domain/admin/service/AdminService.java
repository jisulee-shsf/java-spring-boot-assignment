package com.sparta.domain.admin.service;

import com.sparta.domain.admin.dto.SignupRequestDto;
import com.sparta.domain.admin.dto.SignupResponseDto;
import com.sparta.domain.admin.entity.Admin;
import com.sparta.domain.admin.entity.AdminRoleEnum;
import com.sparta.domain.admin.jwt.JwtUtil;
import com.sparta.domain.admin.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // ✅

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public SignupResponseDto signup(SignupRequestDto requestDto) {

        // 1. email 확인
        String email = requestDto.getEmail();
        Optional<Admin> checkEmail = adminRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 E-mail입니다.");
        }

        // 2. department 확인
        String department = requestDto.getDepartment();
        if (!(department.equals("curriculum") || department.equals("marketing") || department.equals("development"))) {
            throw new IllegalArgumentException("curriculum, marketing, development 부서 중에서만 선택 가능합니다.");
        }

        // 3. role 확인
        AdminRoleEnum role = AdminRoleEnum.STAFF;
        if (requestDto.isManager()) {
            if (!(department.equals("curriculum") || department.equals("development"))) {
                throw new IllegalArgumentException("curriculum 및 development 부서만 관리자 가입이 가능합니다.");
            }
            role = AdminRoleEnum.MANAGER;
        }

        // 4. admin 등록
        String encryptedPassword = passwordEncoder.encode(requestDto.getPassword());
        Admin admin = new Admin(email, encryptedPassword, department, role);
        Admin saveAdmin = adminRepository.save(admin);
        return new SignupResponseDto(saveAdmin);
    }
}