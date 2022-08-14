package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Dto.ChatDto.ChatRoom;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Service.Impl.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;
    private final JwtProvider jwtTokenProvider;
    private final String SUCCESS = "OK";
    private final String FAIL = "ERROR";

    // 모든 채팅방 리스트
    @GetMapping("/rooms")
    public ResponseEntity<?> room() {
        try {
            List<ChatRoom> chatRooms = chatService.findAllRoom();
            return new ResponseEntity<>(chatRooms, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
    }

    // 채팅방 생성
    @PostMapping("/room/{roomName}")
    public ResponseEntity<?> createRoom(@PathVariable("roomName") String roomName) {
        try {
            chatService.createChatRoom(roomName);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

//    @DeleteMapping("/room/{roomId}")
//    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") String roomId){
//        try{
//            chatRoomRepository.deleteChatRoom(roomId);
//        }catch(Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
//        }
//        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> roomInfo(@PathVariable("roomId") String roomId) {
        ChatRoom room;
        try {
            room = chatService.findRoomById(roomId);
            return new ResponseEntity<>(room, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
        }

    }

//    @GetMapping("/user")
//    public ResponseLoginDto getUserInfo() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        return ResponseLoginDto.builder().accessToken(jwtTokenProvider.createAccessToken(name)).build();
//    }
}