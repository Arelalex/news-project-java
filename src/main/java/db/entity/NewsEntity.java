package db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsEntity {

    private Long newsId;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String image;
    private PortalUserEntity user;
    private CategoryEntity category;
    private StatusEntity status;

    public NewsEntity(String title, String description, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String image, PortalUserEntity user, CategoryEntity category, StatusEntity status) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.user = user;
        this.category = category;
        this.status = status;
    }
}
