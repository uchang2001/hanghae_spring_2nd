package com.board.board.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @Pattern(regexp = "[a-zA-Z1-9]",message = "영문자와 숫자로만 입력해주세요")
    @Size(min = 3,message = "최소 3자 이상입니다.")
    private String username;
    @Size(min = 4,message = "최소 4자 이상입니다.")
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}