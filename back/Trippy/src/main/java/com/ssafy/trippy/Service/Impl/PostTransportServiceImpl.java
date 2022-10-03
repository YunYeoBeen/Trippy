package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.Transport;
import com.ssafy.trippy.Repository.PostTransportRepository;
import com.ssafy.trippy.Repository.TransportRepository;
import com.ssafy.trippy.Service.PostTransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostTransportServiceImpl implements PostTransportService{
    private final PostTransportRepository postTransportRepository;

    @Override
    public List<Transport> getTransportByPostTransports(List<PostTransport> postTransports) {
        List<Transport> transports = new ArrayList<>();
        for (PostTransport postTransport : postTransports) {
            transports.add(postTransport.getTransport());
        }
        return transports;
    }
}
