package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Dto.Response.ResponseDetailLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/auth/image")
public class ImageController {

    private final MemberService memberService;

    private final ImageService imageService;

    private final S3Uploader s3Uploader;

//  추후에 업로드 형식에 따라 param받는 형식 고려(현재는 postman이 requestpart에 호환)
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestPart(value = "file") MultipartFile file){
        try {
            ResponseImageDto responseImageDto = s3Uploader.upload(file,"static");
            return new ResponseEntity<>(responseImageDto,HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/upload/member")
    public ResponseEntity<?> updateUserProfile(@RequestPart(value = "file") MultipartFile file, HttpServletRequest request)  {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        ResponseImageDto responseImageDto = null;
        try {
            responseImageDto = s3Uploader.upload(file,"static");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("cannot upload image",HttpStatus.BAD_REQUEST);
        }
        ResponseMemberDto responseMemberDto = memberService.selectMember(memberId);
        s3Uploader.deleteS3(responseMemberDto.getImg_path());
        UpdateMemberDto updateMemberDto = new UpdateMemberDto(responseMemberDto.getName(), responseMemberDto.getEmail(),
                responseMemberDto.getPhone(), responseMemberDto.getGender(),responseMemberDto.getBirth(),responseImageDto.getFileName(),responseMemberDto.getDescription());
        memberService.updateMember(memberId, updateMemberDto);
        s3Uploader.deleteS3(responseMemberDto.getImg_path());
        return new ResponseEntity<>(responseImageDto,HttpStatus.OK);
    }

    @PostMapping("/upload/post")
    public ResponseEntity<?> updatePostProfile(@RequestPart(value = "file") MultipartFile file){
        ResponseImageDto responseImageDto = null;
        try {
            responseImageDto = s3Uploader.upload(file,"static");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("cannot upload image",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseImageDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImage(@RequestParam("filename") String filename) {
        s3Uploader.deleteS3(filename);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<?> getImagesByPostId(@PathVariable("post_id") Long postId) {
        List<ResponseImageDto> images = imageService.getImagesByPostId(postId);
        return new ResponseEntity<>(images,HttpStatus.OK);
    }

}
