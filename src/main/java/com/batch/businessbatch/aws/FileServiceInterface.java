package com.batch.businessbatch.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileServiceInterface {

    File uploadFile(MultipartFile multipartFile, Long id);

}
