package db.service;

import db.dto.StatusFilter;

import java.util.List;

public interface StatusService {

    StatusFilter findById(Integer id);

    List<StatusFilter> findAll();

    StatusFilter save(StatusFilter dto);

    StatusFilter update(StatusFilter dto);

    void delete(StatusFilter dto);

    List<StatusFilter> findAllByFilter(StatusFilter filter);
}
