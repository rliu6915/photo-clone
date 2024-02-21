package com.jetbrains.macrio.photo.clone.service;

import com.jetbrains.macrio.photo.clone.model.Photo;
import com.jetbrains.macrio.photo.clone.repository.PhotozRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Component
@Service
public class PhotoService {
    private final PhotozRepository photozRepository;

    public PhotoService(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }


    public Iterable<Photo> get() {
//        return db;
        return  photozRepository.findAll();
    }

    public Photo get(Integer id) {

//        return db.get(id);
        return photozRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {

//        return db.remove(id);
        photozRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
//        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setData(data);
//        db.put(photo.getId(), photo);
        photozRepository.save(photo);
        return photo;
    }
}
