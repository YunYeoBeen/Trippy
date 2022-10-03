package com.ssafy.trippy.Service.Impl;//package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import com.ssafy.trippy.Repository.*;
import com.ssafy.trippy.Service.PostService;
import com.ssafy.trippy.Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTerm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final DetailLocationRepository detailLocationRepository;
    private final PostTransportRepository postTransportRepository;

    private final TransportRepository transportRepository;

    private final Long busId = 1L;
    private final Long walkId = 2L;
    private final Long taxiId = 3L;


    private final LocationRepository locationRepository;
    private final PostCommentRepository postCommentRepository;
    private final RouteRepository routeRepository;
    private final S3Uploader s3Uploader;

    private final Long walkId = 1L;
    private final Long metroId = 2L;
    private final Long bikeId = 3L;
    private final Long taxiId = 4L;
    private final Long carId = 5L;


    private final List<Long> transportId = new ArrayList<>();
    private final List<Transport> name = new ArrayList<>();


    // 모든 post찾기
    @Override
    public List<ResponsePostDto> findAll() {
        List<Post> all = postRepository.findAll();
        List<ResponsePostDto> responsePostDtoList = new ArrayList<>();
        // 1) 직접 responsePostDto에 set을 해주자!
        for (Post post : all) {
            ResponsePostDto dto = new ResponsePostDto(post);
            responsePostDtoList.add(dto);
        }
        return responsePostDtoList;
    }

    // 유저(본인)가 작성한 게시글 조회
    @Override
    public List<ResponsePostDto> findAllByMember(Member member) {
        List<Post> posts = postRepository.findAllByMember(member);
        List<ResponsePostDto> responsePostDtoUserList = new ArrayList<>();
        for (Post post : posts) {
            ResponsePostDto dto = new ResponsePostDto(post);
            responsePostDtoUserList.add(dto);
        }
        return responsePostDtoUserList;
    }

    // post 등록
    @Transactional
    @Override
    public void savePost(RequestPostDto requestPostDto) {
        Member member = memberRepository.findById(requestPostDto.getMember_id()).get();
        requestPostDto.setMember_id(member.getId());
        Post post = postRepository.save(requestPostDto.toEntity());

        System.out.println("requestPostDto = " + requestPostDto.getPostTransports().get(0).getTransport().getName());
        System.out.println("requestPostDto = " + requestPostDto.getPostTransports().get(0).getTransport().getId());

        for (DetailLocation detailLocation:requestPostDto.toEntity().getDetailLocations()){
            detailLocation.setPost(post);
            detailLocationRepository.save(detailLocation);
        }
        for(PostTransport postTransport:requestPostDto.toEntity().getPostTransports()){
    public Long savePost(RequestPostDto requestPostDto, List<MultipartFile> images) {
        Member member = memberRepository.findById(requestPostDto.getMember_id()).get();
        requestPostDto.setMember_id(member.getId());

        // 요청들어온 post 내의 countryName, cityName으로 location 저장
        Optional<Location> location = locationRepository.findByCountryNameAndCityName(requestPostDto.getCountryName(),requestPostDto.getCityName());
        // location의 Id값을 Post에 저장
        if (location.isPresent()) {
            requestPostDto.setLocation_id(location.get().getId());
        } else {
//            Long locationId = locationRepository.save(Location.builder().countryName(requestPostDto.getCountryName())
//                    .cityName(requestPostDto.getCityName()).build()).getId();
//            requestPostDto.setLocation_id(locationId);
            throw new IllegalArgumentException("잘못된 장소 정보입니다.");
        }

        Post post = postRepository.save(requestPostDto.toEntity());

        List<DetailLocation> detailLocations = requestPostDto.toEntity().getDetailLocations().stream().collect(Collectors.toList());
        for (int i = 0; i < detailLocations.size(); i++) {
            DetailLocation detailLocation = detailLocations.get(i);
            detailLocation.setPost(post);

            if(!images.get(i).getOriginalFilename().equals("empty.txt")) {
                try {
                    ResponseImageDto responseImageDto = s3Uploader.upload(images.get(i),"static");
                    detailLocation.setFilename(responseImageDto.getFileName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            detailLocationRepository.save(detailLocation);
        }
//        for (DetailLocation detailLocation : requestPostDto.toEntity().getDetailLocations().stream().collect(Collectors.toList())) {
//            detailLocation.setPost(post);
//            DetailLocation loc = detailLocationRepository.save(detailLocation);
//        }

//        for(RequestDetailLocationDto requestDetailLocationDto: requestPostDto.getDetailLocations()){
//            List<MultipartFile> images = requestDetailLocationDto.getImages();
//            DetailLocation detailLoc = requestDetailLocationDto.toEntity();
//            detailLoc.setPost(post);
//            DetailLocation detailLocation = detailLocationRepository.save(detailLoc);
//            for (MultipartFile image:images){
//                try {
//                    s3Uploader.upload(image,"static",detailLocation.getId());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new IllegalArgumentException("POST 이미지 저장 error");
//                }
//            }
//        }


        for (PostTransport postTransport : requestPostDto.toEntity().getPostTransports().stream().collect(Collectors.toList())) {
            postTransport.setPost(post);
            postTransportRepository.save(postTransport);
        }

        for (Route route : requestPostDto.toEntity().getRoutes().stream().collect(Collectors.toList())) {
            route.setRoutePost(post);
            routeRepository.save(route);
        }

        return post.getId();
    }

    // post 삭제
    @Transactional
    @Override
    public void deletePost(Long id) {
        List<PostTransport> postTransports = postTransportRepository.findAllPostTransportByPost(Post.builder().id(id).build());
        for (PostTransport postTransport : postTransports) {
            postTransportRepository.delete(postTransport);
        }
        List<Route> routes = routeRepository.findAllByPostId(id);
        for (Route route : routes) {
            routeRepository.delete(route);
        }

        List<PostComment> postComments = postCommentRepository.findPostCommentByPostId(id);
        for (PostComment postComment : postComments) {
            postCommentRepository.delete(postComment);
        }
        postRepository.deleteById(id);
    }

    // post 수정
    @Transactional
    @Override
    public void updatePost(Long id, RequestPostDto requestPostDto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        List<DetailLocation> detailLocationList = post.getDetailLocations();

        // PostTransport 테이블에도 수정된 값 넣어주기
        for (PostTransport postTransport : requestPostDto.toEntity().getPostTransports()) {
            if(postTransport.getTransport().getName().equals("버스")){
                postTransport.getTransport().builder().id(busId).build();
            }else if(postTransport.getTransport().getName().equals("택시")) {
                postTransport.getTransport().builder().id(walkId).build();
            }else {
                postTransport.getTransport().builder().id(taxiId).build();
            }
        }



        // detailLocation 테이블에도 수정된 값 넣어주기
        List<DetailLocation> detailLocation = requestPostDto.toEntity().getDetailLocations();
        List<DetailLocation> detailLocations = new ArrayList<>();
        for(DetailLocation detail:detailLocation){
            detail.setPost(post);
            detailLocations.add(detail);
        }

        for (int i = 0; i < detailLocation.size(); i++) {
            detailLocationList.get(i).update(detailLocations.get(i).getDetailLocationContent(),
                    detailLocations.get(i).getDetailLocationName(),
                    detailLocations.get(i).getRating(),
                    detailLocations.get(i).getImgPath());
        }

        // PostTransport 테이블에도 수정된 값 넣어주기
        List<PostTransport> oldPostTransport = postTransportRepository.findAllPostTransportByPost(post);
        List<PostTransport> postTransport = requestPostDto.toEntity().getPostTransports();
        List<PostTransport> changePostTransportList= new ArrayList<>();
        for(PostTransport postTrans:postTransport){
            postTrans.setPost(post);
            changePostTransportList.add(postTrans);
        }
        System.out.println("changePostTransportList.toString() = " + changePostTransportList.toString());

        for (int i = 0; i < postTransport.size(); i++) {
            oldPostTransport.get(i).update(changePostTransportList.get(i).getTransport());
        }

        post.update(
                requestPostDto.getTitle(),
                requestPostDto.getIsDelete(),
    public void updatePost(Long id, RequestPostDto requestPostDto,List<MultipartFile> images) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        List<DetailLocation> detailLocationList = post.getDetailLocations();


        // PostTransport 테이블에도 수정된 값 넣어주기
        for (PostTransport postTransport : requestPostDto.toEntity().getPostTransports().stream().collect(Collectors.toList())) {
            if (postTransport.getTransport().getName().equals("뚜벅이")) {
                postTransport.getTransport().builder().id(walkId).build();
            } else if (postTransport.getTransport().getName().equals("대중교통")) {
                postTransport.getTransport().builder().id(metroId).build();
            } else if (postTransport.getTransport().getName().equals("따릉이")) {
                postTransport.getTransport().builder().id(bikeId).build();
            } else if (postTransport.getTransport().getName().equals("택시")){
                postTransport.getTransport().builder().id(taxiId).build();
            } else{
                postTransport.getTransport().builder().id(carId).build();
            }

        }
        // detailLocation 테이블에도 수정된 값 넣어주기
        // location의 Id값을 detailLocation에 저장
        Optional<Location> location = locationRepository.findByCountryNameAndCityName( requestPostDto.getCountryName(),requestPostDto.getCityName());
        if (location.isPresent()) {
            requestPostDto.setLocation_id(location.get().getId());
        } else {
            Long locationId = locationRepository.save(Location.builder().countryName(requestPostDto.getCountryName())
                    .cityName(requestPostDto.getCityName()).build()).getId();
            requestPostDto.setLocation_id(locationId);
        }


        List<DetailLocation> oldDetailLocation = detailLocationRepository.findAllByPostId(post.getId()).get();
        List<DetailLocation> newDetailLocations = requestPostDto.toEntity().getDetailLocations().stream().collect(Collectors.toList());
        List<DetailLocation> detailLocationsTmp = new ArrayList<>();

        // 기존의 detailLocation을 삭제
        for (DetailLocation detailLocation : oldDetailLocation) {
            detailLocationRepository.delete(detailLocation);
        }
        // 기존의 detailLocation을 삭제 후 새로운 detailLocation 추가
        for (int i = 0; i < newDetailLocations.size(); i++) {
            DetailLocation detailLocation = newDetailLocations.get(i);
            detailLocation.setPost(post);
            detailLocationRepository.save(detailLocation);
            detailLocationsTmp.add(detailLocation);
            // 빈 이미지나 이미지 파일 이름이 db저장명이랑 똑같으면 처리 x
            if(!images.get(i).getOriginalFilename().equals("empty.txt") & !(images.get(i).getOriginalFilename().equals(newDetailLocations.get(i).getFilename()))) {
                try {
                    ResponseImageDto responseImageDto = s3Uploader.upload(images.get(i),"static");
                    s3Uploader.deleteS3(newDetailLocations.get(i).getFilename());
                    detailLocation.setFilename(responseImageDto.getFileName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        for (int i = 0; i < detailLocationsTmp.size(); i++) {
//            detailLocationList.get(i).update(detailLocationsTmp.get(i).getDetailLocationContent(),
//                    detailLocationsTmp.get(i).getDetailLocationName(),
//                    detailLocationsTmp.get(i).getRating(),
//                    detailLocationsTmp.get(i).getFilename());
//        }

        // PostTransport 테이블에도 수정된 값 넣어주기
        // 기존의 transport 삭제
        List<PostTransport> oldPostTransport = postTransportRepository.findAllPostTransportByPost(post);
        for (PostTransport postTransport : oldPostTransport) {
            postTransportRepository.delete(postTransport);
        }

        // 기존의 transport 삭제 후 새로운 transport 추가
        List<PostTransport> newPostTransport = requestPostDto.toEntity().getPostTransports().stream().collect(Collectors.toList());
        for (PostTransport postTrans : newPostTransport) {
            postTrans.setPost(post);
            postTransportRepository.save(postTrans);
        }

//        for (int i = 0; i < postTransport.size(); i++) {
//            oldPostTransport.get(i).update(changePostTransportList.get(i).getTransport());
//        }

        // Route 테이블에도 수정된 값 넣어주기.
        List<Route> oldRoute = routeRepository.findAllByPostId(post.getId());
        List<Route> newRoutes = requestPostDto.toEntity().getRoutes().stream().collect(Collectors.toList());

        // 기존의 oldRoute 삭제
        for (Route route : oldRoute) {
            routeRepository.delete(route);

        }
        // 기존의 Route 삭제 후 새로운 route 추가
        for (Route newRoute : newRoutes) {
            newRoute.setRoutePost(post);
            routeRepository.save(newRoute);
        }

//        for (int i = 0; i < routes.size(); i++) {
//            oldRoute.get(i).update(changeRoutes.get(i).getRouteName(),
//                    changeRoutes.get(i).getIdx(),
//                    changeRoutes.get(i).getLat(),
//                    changeRoutes.get(i).getLng());
//
//        }
        post.update(
                requestPostDto.getTitle(),
                requestPostDto.getCompany(),
                requestPostDto.getCount(),
                requestPostDto.getStartDate(),
                requestPostDto.getEndDate(),
                requestPostDto.getRepresentiveImg(),
                postTransport,
                detailLocations);
//        System.out.println(postTransportRepository.findAll().toString());
        }

                newPostTransport,
                detailLocationsTmp,
                newRoutes,
                location.get());
    }


    @Override
    public ResponsePostDto findPostId(Long id) {
        Post post = postRepository.findById(id).get();
        Member member = memberRepository.findByEmail(post.getMember().getEmail()).get();
        ResponsePostDto responsePostDto = new ResponsePostDto(post);
        responsePostDto.setMemberId(member.getId());
        return responsePostDto;
    }

    @Override
    public List<ResponsePostDto> findByCity(Location location) {
        List<ResponsePostDto> postDtos = new ArrayList<>();
        Set<Long> postId = new HashSet<>();
        // locationId로 detailLocation찾기
        Optional<Location> location1 = locationRepository.findByCountryNameAndCityName(location.getCountryName(),location.getCityName());
        // detailLocationId로 Post찾기
        List<Post> post = postRepository.findAllByLocationId(location1.get().getId()).orElseThrow();
        for (Post post1 : post) {
            ResponsePostDto dto = new ResponsePostDto(post1);
            postDtos.add(dto);
        }
        return postDtos;
    }

}
