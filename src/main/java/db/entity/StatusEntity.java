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
public class StatusEntity {
    private Integer statusId;
    private Statuses status;

    public StatusEntity(Statuses status) {
        this.status = status;
    }
}
