package com.zaurtregulov.spring.springboot.spring_course_springboot1_2.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class S3Service {

    //@Value("${application.bucket.name}")
    private String bucketName = "springcoursespringboot1copie";

    @Autowired
    private AmazonS3 s3client;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultipartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        //возьмет урл
        //отдельный адд метод имя и урл в таблицу, из дао
        //ля первого задания влада?
        fileObj.delete();
        return "File uploadet: " + fileName;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3object = s3client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        try{
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File ConvertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(ConvertedFile)) {
            fos.write(file.getBytes());
        }
        catch (IOException e) {
            log.error("Error in converting multipart file", e);
        }
        return ConvertedFile;
    }



}
