package db.dto;

import db.entity.CategoryEntity;
import db.entity.PortalUserEntity;
import db.enums.Statuses;
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
    private Statuses status;
    private Integer userId;
    private Integer categoryId;
    private Integer statusId;
    private String reasonRej;

    public NewsDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public NewsDto(Integer userId, Integer categoryId, Integer statusId) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.statusId = statusId;
    }

    public NewsDto(Statuses status) {
        this.status = status;
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
