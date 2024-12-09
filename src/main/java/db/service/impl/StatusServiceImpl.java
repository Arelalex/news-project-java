package db.service.impl;

import db.dao.impl.StatusDaoImpl;
import db.dto.StatusDto;
import db.mapper.StatusMapper;
import db.mapper.impl.StatusMapperImpl;
import db.service.StatusService;

import java.util.List;

public class StatusServiceImpl implements StatusService {

    private static StatusServiceImpl instance;
    private final StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
    private final StatusMapper statusMapper = StatusMapperImpl.getInstance();

    private StatusServiceImpl() {
    }

    public static synchronized StatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new StatusServiceImpl();
        }
        return instance;
    }

    @Override
    public StatusDto findById(Integer id) {
        return null;
    }

    @Override
    public List<StatusDto> findAll() {
        return statusDao.findAll().stream()
                .map(status -> StatusDto.builder()
                        .status(String.valueOf(status.getStatus()))
                        .build()
                )
                .toList();
    }

    @Override
    public StatusDto save(StatusDto dto) {
        return null;
    }

    @Override
    public StatusDto update(StatusDto dto) {
        return null;
    }

    @Override
    public void delete(StatusDto dto) {

    }

    @Override
    public List<StatusDto> findAllByFilter(StatusDto filter) {
        return statusDao.findAllByFilter(filter)
                .stream()
                .map(statusMapper::toDto)
                .toList();
    }
}
