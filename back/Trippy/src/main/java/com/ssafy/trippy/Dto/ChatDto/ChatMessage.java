package com.ssafy.trippy.Dto.ChatDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT;
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
