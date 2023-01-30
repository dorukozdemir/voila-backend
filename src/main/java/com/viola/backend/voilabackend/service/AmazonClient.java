package com.viola.backend.voilabackend.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class AmazonClient {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    @Value("${amazonProperties.region}")
    private String region;

    @PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = AmazonS3ClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(region)
        .build();
    }
    
    public String uploadMultipartFile(MultipartFile multipartFile, String url) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile, url);
            fileUrl = endpointUrl + "/" +  fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }

    public String uploadFile(File file, String fileName) {
        String fileUrl = "";
        try {
            fileUrl = endpointUrl + "/" +  fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }

    public String uploadCompanyPhoto(MultipartFile multipartFile, String url) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateCompanyFileName(multipartFile, url);
            fileUrl = endpointUrl + "/" +  fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart, String url) {
        if(multiPart.getOriginalFilename() != null) {
            final int index = multiPart.getOriginalFilename().lastIndexOf(".");
            String ext = multiPart.getOriginalFilename().substring(index);
            return url + ext;
        }
        return "";
    }

    private String generateCompanyFileName(MultipartFile multiPart, String url) {
        if(multiPart.getOriginalFilename() != null) {
            final int index = multiPart.getOriginalFilename().lastIndexOf(".");
            String ext = multiPart.getOriginalFilename().substring(index);
            return "company-" + url + ext;
        }
        return "";
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }

    public static File getImageFromBase64(String base64String, String fileName) {
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) { // check image's extension
        case "data:image/jpeg;base64":
          extension = "jpeg";
          break;
        case "data:image/png;base64":
          extension = "png";
          break;
        default: // should write cases for more images types
          extension = "jpg";
          break;
        }
        // convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        File file = new File(fileName + extension);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
          outputStream.write(data);
        } catch (IOException e) {
          e.printStackTrace();
        }
        return file;
      }
}