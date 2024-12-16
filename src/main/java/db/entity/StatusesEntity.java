package db.entity;

import db.enums.Statuses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusesEntity {
    private Integer statusId;
    private Statuses status;

    public StatusesEntity(Statuses status) {
        this.status = status;
    }
}
