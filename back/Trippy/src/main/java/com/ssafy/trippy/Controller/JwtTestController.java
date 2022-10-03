package com.ssafy.trippy.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class JwtTestController {

    @PostMapping("/api/valid")
    public String isValid(){
        return "검증 성공";
    }
}
