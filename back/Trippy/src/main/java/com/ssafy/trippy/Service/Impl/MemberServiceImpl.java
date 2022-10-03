package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public Member signup(Member member) { return memberRepository.save(member); }
}
