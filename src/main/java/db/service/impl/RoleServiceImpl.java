package db.service.impl;

import db.dao.impl.RoleDaoImpl;
import db.dto.RoleFilter;
import db.mapper.impl.RoleMapperImpl;
import db.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static RoleServiceImpl instance;
    private final RoleDaoImpl roleDao = RoleDaoImpl.getInstance();
    private final RoleMapperImpl roleMapper = RoleMapperImpl.getInstance();

    private RoleServiceImpl() {
    }

    public static synchronized RoleServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    @Override
    public RoleFilter findById(Integer id) {
        return roleDao.findById(id)
                .map(role -> RoleFilter.builder()
                        .role(String.valueOf(role.getRole()))
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<RoleFilter> findAll() {
        return roleDao.findAll().stream()
                .map(role -> RoleFilter.builder()
                        .role(String.valueOf(role.getRole()))
                        .build()
                )
                .toList();
    }

    @Override
    public RoleFilter save(RoleFilter portalUserFilter) {
        return null;
    }

    @Override
    public RoleFilter update(RoleFilter portalUserFilter) {
        return null;
    }

    @Override
    public void delete(RoleFilter portalUserFilter) {

    }

    @Override
    public List<RoleFilter> findAllByFilter(RoleFilter filter) {
        return roleDao.findAllByFilter(filter)
                .stream()
                .map(roleMapper::toDto)
                .toList();
    }
}
