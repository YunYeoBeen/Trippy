package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.Transport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPostTransPortDto {

    private Post post;

    private Transport transport;

    public PostTransport toEntity(){
        return PostTransport.builder().post(post)
                .transport(transport)
                .build();
    }
}
