package db.service;

import db.util.PropertiesUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class ImageService {

    private static ImageService instance;
    private final String basePath = PropertiesUtil.get("image.base.url");

    private ImageService() {
    }

    public static synchronized ImageService getInstance() {
        if (instance == null) {
            instance = new ImageService();
        }
        return instance;
    }

    public void upload(String imagePath, InputStream imageContent) {
        var imageFullPath = Path.of(basePath, imagePath);
        try(imageContent){
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(basePath, imagePath);
        try {
            return Files.exists(imageFullPath)
                    ? Optional.of(Files.newInputStream(imageFullPath))
                    : Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
