package db.dto;

import db.entity.CategoryEntity;
import db.entity.PortalUserEntity;
import db.entity.StatusEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    private Integer limit;
    private Integer offset;
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
    private Integer userId;

    public NewsDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public NewsDto(Integer userId) {
        this.userId = userId;
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdAt.format(formatter);
    }

    public String getFormattedUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return updatedAt.format(formatter);
    }
}
