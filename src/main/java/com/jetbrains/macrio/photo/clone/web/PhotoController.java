package com.jetbrains.macrio.photo.clone.web;

import com.jetbrains.macrio.photo.clone.model.Photo;
import com.jetbrains.macrio.photo.clone.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotoController {
//    private Map<String, Photo> db = new HashMap<>() {{
//        put("1", new Photo("1", "photo1.jpg"));
//    }};

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> getPhotos() {

        return photoService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhoto(@PathVariable Integer id) {
        Photo photo = photoService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void deletePhoto(@PathVariable Integer id) {
        photoService.remove(id);
//        if (photo == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
//        }
    }

//    @PostMapping("/photoz")
//    public Photo create(@RequestBody @Valid  Photo photo) {
//        photo.setId(UUID.randomUUID().toString());
//        db.put(photo.getId(), photo);
//        return photo;
//    }
    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
//        Photo photo = new Photo();
//        photo.setId(UUID.randomUUID().toString());
//        photo.setFileName(file.getOriginalFilename());
//        photo.setData(file.getBytes());
        // photoService.put(photo.getId(), photo);
        // Photo photo = photoService.save(file.getOriginalFilename(), file.getBytes());
        return photoService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
