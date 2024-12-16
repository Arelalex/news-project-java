package db.mapper;

import db.dto.EditNewsDto;
import db.entity.NewsEntity;

public interface EditNewsMapper extends BaseMapper<NewsEntity, EditNewsDto> {

    NewsEntity toEntity(EditNewsDto dto);
}
