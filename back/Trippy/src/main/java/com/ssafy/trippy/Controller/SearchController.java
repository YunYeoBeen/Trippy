package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Service.Impl.PostSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {
    private final PostSearchServiceImpl postSearchService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value="title", required = false) String title,
                                    @RequestParam(value="company", required = false) Integer company,
                                    @RequestParam(value="transportId", required = false) Long transportId){
        if(company==null) company = 0;
        if(transportId==null) transportId = 0L;

        SearchRequestDto searchRequestDto = SearchRequestDto.builder()
                .company(company)
                .title(title)
                .transportId(transportId)
                .build();
        List<ResponsePostDto> post;
        try{
            post =  postSearchService.searchPost(searchRequestDto);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("해당 게시물을 찾을 수 없습니다.",HttpStatus.BAD_REQUEST );
        }
        return new ResponseEntity<List<ResponsePostDto>>(post, HttpStatus.OK);
    }
}
