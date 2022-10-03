package com.ssafy.trippy.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
public class ResponseMemberDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private int gender;
    private LocalDateTime birth;
    private String img_path;
    private String description;
    private String img_link;

    //Entity -> Dto
    public ResponseMemberDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.gender = member.getGender();
        this.birth = member.getBirth();
        this.img_path = member.getImg_path();
        this.description = member.getDescription();
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }
}
