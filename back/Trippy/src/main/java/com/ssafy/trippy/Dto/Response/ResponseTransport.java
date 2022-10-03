package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.Transport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseTransport {

    private String name;

    @Builder
    public ResponseTransport (Transport transport){
        this.name = transport.getName();
    }


}
