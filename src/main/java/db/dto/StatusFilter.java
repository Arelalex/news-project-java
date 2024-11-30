package db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StatusFilter {
    private int limit;
    private int offset;
    private String status;
}
