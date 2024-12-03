package db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentFilter {
    private Integer limit;
    private Integer offset;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
