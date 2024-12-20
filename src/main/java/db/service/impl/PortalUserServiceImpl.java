package db.service.impl;

import db.dao.impl.PortalUserDaoImpl;
import db.dto.PortalUserDto;
import db.mapper.PortalUserMapper;
import db.mapper.impl.PortalUserMapperImpl;
import db.service.PortalUserService;

import java.util.List;
import java.util.NoSuchElementException;

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
    public PortalUserDto findById(Integer id) {
        return portalUserDao.findById(id)
                .map(user -> PortalUserDto.builder()
                        .userId(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .image(user.getImage())
                        .role(user.getRole())
                        .build()
                )
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public List<PortalUserDto> findAll() {
        return portalUserDao.findAll()
                .stream()
                .map(portalUserMapper::toDto)
                .toList();
    }

    @Override
    public PortalUserDto save(PortalUserDto portalUserDto) {
        return null;
    }

    @Override
    public PortalUserDto update(PortalUserDto portalUserDto) {
        return null;
    }

    @Override
    public void delete(PortalUserDto portalUserDto) {

    }

    @Override
    public List<PortalUserDto> findAllByFilter(PortalUserDto filter) {
        return portalUserDao.findAllByFilter(filter)
                .stream()
                .map(portalUserMapper::toDto)
                .toList();
    }
}
