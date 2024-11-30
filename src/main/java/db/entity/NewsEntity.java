package db.entity;

import db.dto.PortalUserFilter;
import db.enums.Statuses;
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

    private Integer id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String image;
    private PortalUserEntity user;
    private CategoryEntity category;
    private StatusEntity status;

    public NewsEntity(String title, String description, String content, LocalDateTime createAt, LocalDateTime updateAt, String image, PortalUserEntity user, CategoryEntity category, StatusEntity status) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.image = image;
        this.user = user;
        this.category = category;
        this.status = status;
    }
}
