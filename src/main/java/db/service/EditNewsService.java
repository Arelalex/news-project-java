package db.service;

import db.dto.CreateNewsDto;
import db.dto.EditNewsDto;

public interface EditNewsService {

    Long update(EditNewsDto newsDto);

}
