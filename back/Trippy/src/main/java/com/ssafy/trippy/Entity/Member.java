package com.ssafy.trippy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    @Length(min=1, max=20,message="이름 사이즈를 확인하세요")
    @NotBlank(message="이름은 필수값입니다.")
    private String name;

    //    @JsonIgnore
    @Column(name="PW", nullable = false, length=15)
    @NotBlank(message="비밀번호는 필수값입니다.")
    private String password;

    @Column(nullable = false, length=60)
    @Length(min=1, max=50,message="이메일 사이즈를 확인하세요")
    @NotBlank(message="이메일은 필수값입니다.")
    private String email;

    @Column(nullable = false, length=15)
    @NotBlank(message="핸드폰 번호는 필수값입니다.")
    private String phone;

    @Column(nullable = false, length=2)
    @NotBlank(message="성별은 필수값입니다.")
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length=15)
    @NotBlank(message="생일은 필수값입니다.")
    private Timestamp birth;

    //    @JsonIgnore
    private String img_path;

    //    @JsonIgnore
    private String desc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length=15)
    @NotBlank(message="생성일자는 필수값입니다.")
    private Timestamp createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, length=15)
    @NotBlank(message="수정날짜는 필수값입니다.")
    private Timestamp updateAt;



}
