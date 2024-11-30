package db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CommentFilter {
    int limit;
    int offset;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
