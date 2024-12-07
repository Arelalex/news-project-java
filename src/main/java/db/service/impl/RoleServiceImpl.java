package db.service.impl;

import db.dao.impl.RoleDaoImpl;
import db.dto.RoleDto;
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
    public RoleDto findById(Integer id) {
        return roleDao.findById(id)
                .map(role -> RoleDto.builder()
                        .role(String.valueOf(role.getRole()))
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleDao.findAll().stream()
                .map(role -> RoleDto.builder()
                        .role(String.valueOf(role.getRole()))
                        .build()
                )
                .toList();
    }

    @Override
    public RoleDto save(RoleDto portalUserFilter) {
        return null;
    }

    @Override
    public RoleDto update(RoleDto portalUserFilter) {
        return null;
    }

    @Override
    public void delete(RoleDto portalUserFilter) {

    }

    @Override
    public List<RoleDto> findAllByFilter(RoleDto filter) {
        return roleDao.findAllByFilter(filter)
                .stream()
                .map(roleMapper::toDto)
                .toList();
    }
}
