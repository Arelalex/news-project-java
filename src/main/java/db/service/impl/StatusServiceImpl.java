package db.service.impl;

import db.dao.impl.StatusDaoImpl;
import db.dto.StatusFilter;
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
    public StatusFilter findById(Integer id) {
        return null;
    }

    @Override
    public List<StatusFilter> findAll() {
        return statusDao.findAll().stream()
                .map(status -> StatusFilter.builder()
                        .status(String.valueOf(status.getStatus()))
                        .build()
                )
                .toList();
    }

    @Override
    public StatusFilter save(StatusFilter dto) {
        return null;
    }

    @Override
    public StatusFilter update(StatusFilter dto) {
        return null;
    }

    @Override
    public void delete(StatusFilter dto) {

    }

    @Override
    public List<StatusFilter> findAllByFilter(StatusFilter filter) {
        return statusDao.findAllByFilter(filter)
                .stream()
                .map(entity -> statusMapper.toDto(entity))
                .toList();
    }
}
