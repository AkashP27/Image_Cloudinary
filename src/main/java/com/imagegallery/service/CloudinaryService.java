package com.imagegallery.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private Cloudinary cloudinary;

    public CloudinaryService(@Value("${cloud.name}") String cloudName,
                             @Value("${cloud.api.key}") String apiKey,
                             @Value("${cloud.api.secret}") String apiSecret) {

        credentials.put("cloud_name",cloudName);
        credentials.put("api_key",apiKey);
        credentials.put("api_secret",apiSecret);

        cloudinary = new Cloudinary(credentials);
    }

    private Map<String,String> credentials = new HashMap<>();

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "spring_boot/image_gallery"));
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.asMap("resource_type","image"));
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
