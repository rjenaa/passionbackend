package com.batch.businessbatch.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class FileService implements FileServiceInterface{

    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.s3.bucket}")
    private String bucketName;


    @Value("${aws.url}")
    private String bucketURL;


    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    public File convertFile(MultipartFile multipartFile) {
        LOGGER.info("Converting multipart to file ...");
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            LOGGER.error("Error finding file: "+multipartFile.getOriginalFilename());
        }
        try {
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            LOGGER.error("Error while writing to new file using file : "+multipartFile.getOriginalFilename());
        }
        return file;
    }

    @Override
    public File uploadFile(MultipartFile multipartFile, Long Id) {

        LOGGER.info("Resume upload in progress.");

        File uploadFile = convertFile(multipartFile);

        String updatedName = Id + multipartFile.getOriginalFilename();

        LOGGER.info("Uploading file with name= " + updatedName);

        final PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName, updatedName, uploadFile);

        LOGGER.info("Setting metadata for file: " + uploadFile.getName());

//        ObjectMetadata objectMetadata = new ObjectMetadata();
//
//        objectMetadata.setContentType("application/pdf");
//
//        putObjectRequest.setMetadata(objectMetadata);

        uploadToS3(putObjectRequest);



        return uploadFile;

    }

    public File getObject(String key){
        File file = new File("src/main/resources/tempfile");
        amazonS3.getObject(new GetObjectRequest(bucketName, key), file);
        return  file;
    }

    private void uploadToS3(PutObjectRequest putObjectRequest){
        amazonS3.putObject(putObjectRequest);
        LOGGER.info("Successfully uploaded file with name: " + putObjectRequest.getKey());
    }
}
