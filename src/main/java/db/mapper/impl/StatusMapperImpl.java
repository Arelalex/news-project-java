package db.mapper.impl;

import db.dto.StatusDto;
import db.entity.StatusesEntity;
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
    public StatusesEntity toEntity(StatusDto dto) {
        return StatusesEntity
                .builder()
                .statusId(dto.getStatusId())
                .status(Statuses.valueOf(dto.getStatus()))
                .build();
    }

    @Override
    public StatusDto toDto(StatusesEntity entity) {
        return StatusDto
                .builder()
                .statusId(entity.getStatusId())
                .status(String.valueOf(entity.getStatus()))
                .build();
    }
}
