package db.service;

import db.dto.PortalUserDto;

import java.util.List;

public interface PortalUserService {

    PortalUserDto findById(Integer id);

    List<PortalUserDto> findAll();

    PortalUserDto save(PortalUserDto portalUserDto);

    PortalUserDto update(PortalUserDto portalUserDto);

    void delete(PortalUserDto portalUserDto);

}
