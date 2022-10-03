package com.ssafy.trippy.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.ssafy.trippy.Domain.Image;
import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Repository.ImageRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    private final ImageRespository imageRespository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ResponseImageDto upload(MultipartFile multipartFile, String dirName) throws IOException {

        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return upload(uploadFile, dirName);
    }

    private ResponseImageDto upload(File uploadFile, String dirName) {

        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);

        Image image = Image.builder()
                .fileName(fileName)
                .fileUrl(uploadImageUrl)
                .build();
//        if(DetailLocId != null) imageRespository.save(image);
        removeNewFile(uploadFile);
        return new ResponseImageDto(image);
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public String getS3(String fileName) {
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public Resource getObject(String fileName) throws IOException {
        if(fileName == null){
            fileName = "static/noImg.jpg";
        }
        S3Object o = amazonS3Client.getObject(new GetObjectRequest(bucket, fileName));
        S3ObjectInputStream objectInputStream = o.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        Resource resource = new ByteArrayResource(bytes);
        return resource;
    }

    public void deleteS3(String fileName){
        DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
        amazonS3Client.deleteObject(request);
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        String sourceFileNameExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        String destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + sourceFileNameExtension;
        File convertFile = new File(destinationFileName);
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
