package db.service.impl;

import db.dao.impl.NewsDaoImpl;
import db.dto.CreateNewsDto;
import db.enums.Statuses;
import db.exception.ValidationException;
import db.mapper.impl.CreateNewsMapperImpl;
import db.service.CreateNewsService;
import db.service.ImageService;
import db.validator.CreateNewsValidator;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateNewsServiceImpl implements CreateNewsService {

    private static CreateNewsServiceImpl instance;
    private final NewsDaoImpl newsDao = NewsDaoImpl.getInstance();
    private final CreateNewsMapperImpl createNewsMapper = CreateNewsMapperImpl.getInstance();
    private final CreateNewsValidator createNewsValidator = CreateNewsValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private CreateNewsServiceImpl() {
    }

    public static synchronized CreateNewsServiceImpl getInstance() {
        if (instance == null) {
            instance = new CreateNewsServiceImpl();
        }
        return instance;
    }

    public Long create(CreateNewsDto newsDto) {
        var validationResult = createNewsValidator.isValid(newsDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var newsEntity = createNewsMapper.toEntity(newsDto);

        newsEntity.setCreatedAt(LocalDateTime.now());
        newsEntity.setUpdatedAt(LocalDateTime.now());
        try {
            imageService.upload(newsEntity.getImage(), newsDto.getImage().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newsEntity.setStatus(Statuses.ON_MODERATION);

        newsDao.save(newsEntity);

        return newsEntity.getNewsId();
    }
}
