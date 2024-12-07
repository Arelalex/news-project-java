package db.service;

import db.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto findById(Integer id);

    List<RoleDto> findAll();

    RoleDto save(RoleDto portalUserFilter);

    RoleDto update(RoleDto portalUserFilter);

    void delete(RoleDto portalUserFilter);

    List<RoleDto> findAllByFilter(RoleDto filter);
}
