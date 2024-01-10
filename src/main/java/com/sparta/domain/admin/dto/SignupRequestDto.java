package com.sparta.domain.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Email(message = "올바른 이메일 형식을 입력해 주세요.")
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 8자에서 15자 사이의 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다."
    )
    private String password;

    @NotBlank
    private String department;

    private boolean manager;
}