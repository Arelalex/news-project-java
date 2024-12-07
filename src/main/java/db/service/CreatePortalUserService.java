package db.service;

import db.dto.CreatePortalUserDto;
import db.dto.PortalUserDto;

import java.util.List;

public interface CreatePortalUserService {

    Integer create(CreatePortalUserDto userDto);

}
