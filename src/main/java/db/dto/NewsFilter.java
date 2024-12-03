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
public class NewsFilter {

    private Integer limit;
    private Integer offset;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String image;
    private String user;
    private Integer category;
    private String status;

    public NewsFilter(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
