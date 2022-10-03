package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails{
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
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
    private List<LikePost> likePosts = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followings = new ArrayList<>();

    @Builder
    public Member(Long id, String name, String password, String email, String phone, int gender, LocalDateTime birth, String img_path, String description, List<Bookmark> bookmarks, List<LikePost> likePosts) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.img_path = img_path;
        this.description = description;
        this.bookmarks = bookmarks;
        this.likePosts = likePosts;
    }

    public void update(UpdateMemberDto updateMemberDto){
        this.name = updateMemberDto.getName();
        this.email = updateMemberDto.getEmail();
        this.phone = updateMemberDto.getPhone();
        this.gender = updateMemberDto.getGender();
        this.birth = updateMemberDto.getBirth();
        this.img_path = updateMemberDto.getImg_path();
        this.description = updateMemberDto.getDescription();
    }

    public void updatePw(String pw){
        this.password = pw;
    }

    @Override
    public String getPassword(){
        return this.password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled() {
        return true;
    }
}
