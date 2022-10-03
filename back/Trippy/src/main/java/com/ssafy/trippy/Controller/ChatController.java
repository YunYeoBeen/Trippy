package com.ssafy.trippy.Controller;


import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Dto.ChatDto.ChatMessage;
import com.ssafy.trippy.Dto.ChatDto.ChatRoom;
import com.ssafy.trippy.Service.Impl.ChatService;
import com.ssafy.trippy.Service.Publisher.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class ChatController {

//    private final JwtProvider jwtTokenProvider;
    private final RedisPublisher redisPublisher;
    private final ChatService chatService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
//    @MessageMapping("/chat/message")
//    public void message(ChatMessage message, @Header("token") String token) {
//        String email = jwtTokenProvider.getUserPk(token);
//        // 로그인 회원 정보로 대화명 설정
//        message.setSender(email);
//        // 채팅방 인원수 세팅
//        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
//        // Websocket에 발행된 메시지를 redis로 발행(publish)
//        chatService.sendChatMessage(message);
//    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if(ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatService.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다. 환영합니다");
        }
//        }else if(ChatMessage.MessageType.QUIT.equals(message.getMessage())){
//            message.setMessage(message.getSender()+"님이 퇴장하셨습니다.");
//            chatService.deleteById(ChatRoom.builder().roomId(message.getRoomId()).build());
//        }
        redisPublisher.publish(chatService.getTopic(message.getRoomId()), message);
    }
}