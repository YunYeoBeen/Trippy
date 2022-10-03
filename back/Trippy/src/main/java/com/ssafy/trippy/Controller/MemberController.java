package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    private void signup(@RequestBody Member member) {
        memberService.signup(member);
    }
}
