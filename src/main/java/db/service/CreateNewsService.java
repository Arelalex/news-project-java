package db.service;

import db.dto.CreateNewsDto;
import db.dto.CreatePortalUserDto;

public interface CreateNewsService {

    Long create(CreateNewsDto newsDto);

}
