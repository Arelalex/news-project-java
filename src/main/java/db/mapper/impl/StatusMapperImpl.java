package db.mapper.impl;

import db.dto.StatusFilter;
import db.entity.StatusEntity;
import db.enums.Statuses;
import db.mapper.StatusMapper;

public class StatusMapperImpl implements StatusMapper {

    private static StatusMapperImpl instance;

    private StatusMapperImpl() {
    }

    public static StatusMapperImpl getInstance() {
        if (instance == null) {
            instance = new StatusMapperImpl();
        }
        return instance;
    }

    @Override
    public StatusEntity toEntity(StatusFilter dto) {
        return StatusEntity
                .builder()
                .status(Statuses.valueOf(dto.getStatus()))
                .build();
    }

    @Override
    public StatusFilter toDto(StatusEntity entity) {
        return StatusFilter
                .builder()
                .status(String.valueOf(entity.getStatus()))
                .build();
    }
}
