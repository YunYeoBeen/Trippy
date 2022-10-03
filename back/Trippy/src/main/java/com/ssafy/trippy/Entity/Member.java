package com.ssafy.trippy.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

//    @Column(nullable = false)
//    @Length(min=1, max=20,message="이름 사이즈를 확인하세요")
//    @NotBlank(message="이름은 필수값입니다.")
    private String name;

//    @JsonIgnore
//    @Column(nullable = false, length=15)
//    @NotBlank(message="비밀번호는 필수값입니다.")
    private String password;

//    @Column(nullable = false, length=60)
//    @Length(min=1, max=50,message="이메일 사이즈를 확인하세요")
//    @NotBlank(message="이메일은 필수값입니다.")
    private String email;

//    @Column(nullable = false, length=15)
//    @NotBlank(message="핸드폰 번호는 필수값입니다.")
    private String phone;

//    @Column(nullable = false, length=2)
//    @NotBlank(message="성별은 필수값입니다.")
    private int gender;

//    @Column(nullable = false, length=15)
//    @NotBlank(message="생일은 필수값입니다.")
    private LocalDateTime birth;

//    @JsonIgnore
    private String img_path;

//    @JsonIgnore
    private String desc;
    @OneToMany(mappedBy="member")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy="member")
    private List<LikePost> likePosts = new ArrayList<>();

    @Builder
    public Member(Long id, String name, String password, String email, String phone, int gender, LocalDateTime birth, String img_path, String desc, List<Bookmark> bookmarks, List<LikePost> likePosts) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.img_path = img_path;
        this.desc = desc;
        this.bookmarks = bookmarks;
        this.likePosts = likePosts;
    }

}
