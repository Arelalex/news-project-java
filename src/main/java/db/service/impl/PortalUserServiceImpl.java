package db.service.impl;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.PortalUserFilter;
import db.entity.PortalUserEntity;
import db.mapper.PortalUserMapper;
import db.mapper.impl.PortalUserMapperImpl;
import db.service.PortalUserService;

import java.util.List;

public class PortalUserServiceImpl implements PortalUserService {

    private static PortalUserServiceImpl instance;
    private final PortalUserDaoImpl portalUserDao = PortalUserDaoImpl.getInstance();
    private final PortalUserMapper portalUserMapper = PortalUserMapperImpl.getInstance();

    private PortalUserServiceImpl() {
    }

    public static synchronized PortalUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new PortalUserServiceImpl();
        }
        return instance;
    }

    @Override
    public PortalUserFilter findById(Integer id) {
        return portalUserDao.findById(id)
                .map(user -> PortalUserFilter.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .build()
                )
                .orElse(null);
    }

    @Override
    public List<PortalUserFilter> findAll() {
        return portalUserDao.findAll()
                .stream()
                .map(portalUserMapper::toDto)
                .toList();
    }

    @Override
    public PortalUserFilter save(PortalUserFilter portalUserFilter) {
        return null;
    }

    @Override
    public PortalUserFilter update(PortalUserFilter portalUserFilter) {
        return null;
    }

    @Override
    public void delete(PortalUserFilter portalUserFilter) {

    }
}
