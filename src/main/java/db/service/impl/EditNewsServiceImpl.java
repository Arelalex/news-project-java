package db.service.impl;

import db.dao.impl.NewsDaoImpl;
import db.dto.EditNewsDto;
import db.enums.Statuses;
import db.exception.ValidationException;
import db.mapper.impl.EditNewsMapperImpl;
import db.service.EditNewsService;
import db.service.ImageService;
import db.validator.EditNewsValidator;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

public class EditNewsServiceImpl implements EditNewsService {

    private static EditNewsServiceImpl instance;
    private final NewsDaoImpl newsDao = NewsDaoImpl.getInstance();
    private final EditNewsMapperImpl editNewsMapper = EditNewsMapperImpl.getInstance();
    private final EditNewsValidator editNewsValidator = EditNewsValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private EditNewsServiceImpl() {
    }

    public static synchronized EditNewsServiceImpl getInstance() {
        if (instance == null) {
            instance = new EditNewsServiceImpl();
        }
        return instance;
    }

    @SneakyThrows
    public Long update(EditNewsDto newsDto) {
        var validationResult = editNewsValidator.isValid(newsDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var newsEntity = editNewsMapper.toEntity(newsDto);
        newsEntity.setUpdatedAt(LocalDateTime.now());
        newsEntity.setStatus(Statuses.ON_MODERATION);

        imageService.upload(newsEntity.getImage(), newsDto.getImage().getInputStream());

        newsDao.update(newsEntity);

        return newsEntity.getNewsId();
    }
}
