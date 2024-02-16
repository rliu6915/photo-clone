package com.jetbrains.macrio.photo.clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotoController {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "photo1.jpg"));
    }};

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/photoz")
    public Map<String, Photo> getPhotos() {
        return db;
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhoto(@PathVariable String id) {
        Photo photo = db.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void deletePhoto(@PathVariable String id) {
        Photo photo = db.remove(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
    }

//    @PostMapping("/photoz")
//    public Photo create(@RequestBody @Valid  Photo photo) {
//        photo.setId(UUID.randomUUID().toString());
//        db.put(photo.getId(), photo);
//        return photo;
//    }
    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        db.put(photo.getId(), photo);
        return photo;
    }
}
