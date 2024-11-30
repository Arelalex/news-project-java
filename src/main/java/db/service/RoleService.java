package db.service;

import db.dto.RoleFilter;

import java.util.List;

public interface RoleService {

    RoleFilter findById(Integer id);

    List<RoleFilter> findAll();

    RoleFilter save(RoleFilter portalUserFilter);

    RoleFilter update(RoleFilter portalUserFilter);

    void delete(RoleFilter portalUserFilter);

    List<RoleFilter> findAllByFilter(RoleFilter filter);
}
