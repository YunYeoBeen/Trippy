package com.ssafy.trippy.Service;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestLoginDto;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;

public interface MemberService {

    ResponseMemberDto signup(RequestMemberDto requestMemberDto);

    ResponseLoginDto login(String email, String password);

    ResponseLoginDto reIssueAccessToken(String email, String refreshToken);

    boolean chkDuplicate(String email);

    void deleteMember(Long id);

    void updateMember(Long id, UpdateMemberDto updateMemberDto);

    ResponseMemberDto selectMember(Long id);

    void logout(Long id, String accessToken);

    void changePw(RequestLoginDto requestLoginDto);

    Long getIdByToken(String token);
}
