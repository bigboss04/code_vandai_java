package com.example.vandai.com.vn.utils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.detect.Detector;
import org.apache.tika.parser.AutoDetectParser;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;


public class FileUtils {
  private static final String UPLOADS_FOLDER = "uploads";

//  block file tampering . security server
  private static boolean isImageFile(MultipartFile file) {
//    automatic identify file  / no need to know the file type in advance
    AutoDetectParser parser = new AutoDetectParser();
//    analysis byte // return the actual file MIME type
    Detector detector = parser.getDetector();
    try{
      org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
      org.apache.tika.io.TikaInputStream stream = org.apache.tika.io.TikaInputStream.get(file.getInputStream());
      org.apache.tika.mime.MediaType mediaType = detector.detect(stream,metadata);
      String mimeType = mediaType.toString();
      return mimeType != null && mimeType.startsWith("image/");
    } catch (IOException e) {
      return false;
    }
  }


  public static String storeFile(MultipartFile file) throws Exception {
    System.out.println(">>> STORE FILE CALLED");
    if(!isImageFile(file) || file.getOriginalFilename() == null){
      throw new Exception("Invalid image format");
    }
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    String extension = FilenameUtils.getExtension(fileName);
    //UUID and extension name file
    String uniqueFileName = UUID.randomUUID().toString() + "_" +System.nanoTime() + "." + extension;

    //url den file
    Path uploadDir = Paths.get("uploads");
    if(!Files.exists(uploadDir)){
      Files.createDirectories(uploadDir);
    }
//    duong dan day du den file
    Path destinationPath = Paths.get(uploadDir.toString(),uniqueFileName);
//    copy file to destination
    Files.copy(file.getInputStream(),destinationPath, StandardCopyOption.REPLACE_EXISTING);
    return uniqueFileName;

  }
}
