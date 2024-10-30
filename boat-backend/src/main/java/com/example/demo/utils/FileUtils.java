package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

public class FileUtils {

    public static String convertMultipartFileToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}