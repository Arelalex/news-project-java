package db.mapper;

import db.dto.CreateNewsDto;
import db.entity.NewsEntity;

public interface CreateNewsMapper extends BaseMapper<NewsEntity, CreateNewsDto> {

    NewsEntity toEntity(CreateNewsDto dto);
}
