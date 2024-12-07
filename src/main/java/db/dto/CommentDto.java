package db.dto;

import db.entity.NewsEntity;
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
public class CommentDto {
    private Integer limit;
    private Integer offset;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long newsId;
    private NewsEntity news;
    private PortalUserEntity user;
    private StatusEntity status;

    public CommentDto(Long newsId) {
        this.newsId = newsId;
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