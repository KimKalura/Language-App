//package com.spring.languageapp.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@RestController
//public class FileUploadController {
//    @Value("${file.upload-dir}")
//    String FILE_DIRECTORY;
//
//    @PostMapping("/uploadFile")
//    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException { //nu creaza folderul; inf de pe:https://www.youtube.com/watch?v=PQb55jRTrxY&ab_channel=GainJavaKnowledge
//        File myFile = new File((FILE_DIRECTORY+file.getOriginalFilename()));
//        myFile.createNewFile();
//        FileOutputStream fos =new FileOutputStream(myFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
//    }


            //PT APPLICATION PROPERTIES
    //# Enable multipart uploads
    //spring.servlet.multipart.enabled=true
    //# Threshold after which files are written to disk.
    //spring.servlet.multipart.file-size-threshold=2KB
    //# Max file size.
    //spring.servlet.multipart.max-file-size=200KB
    //# Max Request Size
    //spring.servlet.multipart.max-request-size=200KB
    //
    //## File Storage Properties
    //# All files uploaded through the REST API will be stored in this directory
    //file.upload-dir=D:\\storage\\
//}
