package com.imagegallery.controller;

import com.imagegallery.dto.Message;
import com.imagegallery.entity.Image;
import com.imagegallery.service.CloudinaryService;
import com.imagegallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")

public class MainController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>(new Message("Image invalid"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        System.out.println(result);

        Image image = new Image((String)result.get("original_filename"),
                (String)result.get("secure_url"),
                (String) result.get("public_id"));

        imageService.save(image);
        return new ResponseEntity<>(new Message("Image uploaded successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Image>> getImage() {
        List<Image> images = imageService.get();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{imageId}/{id}")
    public ResponseEntity<?> delete(@PathVariable("imageId") String imageId,
                                    @PathVariable("id") int id) throws IOException {

        if(!imageService.exists(id))
            return new ResponseEntity<>(new Message("Image doesn't exist"), HttpStatus.NOT_FOUND);
        Image image = imageService.getOne(id).get();
        System.out.println(imageService.getOne(id).get());
        Map result = cloudinaryService.delete(imageId);
        imageService.delete(id);
        return new ResponseEntity<>(new Message("Image deleted"), HttpStatus.NO_CONTENT);
    }
}

