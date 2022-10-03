package com.ssafy.trippy.Service;

import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.Transport;

import java.util.List;

public interface PostTransportService {
    List<Transport> getTransportByPostTransports(List<PostTransport> postTransports);
}
