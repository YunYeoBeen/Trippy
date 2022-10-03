package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.*;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestPostTransportDto {

    private Post post;
    private Transport transport;

    public PostTransport toEntity(){
        return PostTransport.builder()
                .transport(transport)
                .post(post).build();
    }
    @Builder
    public RequestPostTransportDto(Post post, Transport transport) {
        this.post = post;
        this.transport = transport;
    }
}
