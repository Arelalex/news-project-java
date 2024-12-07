package db.service;

import db.dto.StatusDto;

import java.util.List;

public interface StatusService {

    StatusDto findById(Integer id);

    List<StatusDto> findAll();

    StatusDto save(StatusDto dto);

    StatusDto update(StatusDto dto);

    void delete(StatusDto dto);

    List<StatusDto> findAllByFilter(StatusDto filter);
}
