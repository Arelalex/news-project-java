package db.mapper.impl;

import db.dto.StatusDto;
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
    public StatusEntity toEntity(StatusDto dto) {
        return StatusEntity
                .builder()
                .status(Statuses.valueOf(dto.getStatus()))
                .build();
    }

    @Override
    public StatusDto toDto(StatusEntity entity) {
        return StatusDto
                .builder()
                .status(String.valueOf(entity.getStatus()))
                .build();
    }
}
