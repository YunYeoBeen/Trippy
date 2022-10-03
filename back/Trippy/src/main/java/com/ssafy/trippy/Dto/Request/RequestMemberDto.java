package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Member;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestMemberDto {

    @ApiParam(value = "사용자 이름")
    private String name;
    @ApiParam(value = "비밀번호")
    private String password;
    @ApiParam(value = "이메일")
    private String email;
    @ApiParam(value = "전화번호")
    private String phone;
    @ApiParam(value = "성별")
    private int gender;
    @ApiParam(value = "생일")
    private LocalDateTime birth;
    @ApiParam(value = "프로필 이미지 경로")
    private String img_path;
    @ApiParam(value = "설명")
    private String description;

    // DTO -> ENTITY
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .phone(phone)
                .gender(gender)
                .birth(birth)
                .img_path(img_path)
                .description(description)
                .build();
    }

    public void encodePassword(String encodedPassword){
        this.password = encodedPassword;
    }
}
