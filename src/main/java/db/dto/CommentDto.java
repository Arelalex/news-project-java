package db.dto;

import db.entity.NewsEntity;
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
public class CommentDto {
    private final String DATE_PATTERN = "dd.MM.yyyy HH:mm";
    private Long commentId;
    private Integer limit;
    private Integer offset;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String attachment;
    private Long newsId;
    private NewsEntity news;
    private Integer userId;
    private PortalUserEntity user;
    private Integer statusId;
    private Statuses status;
    private String reasonRej;

    public CommentDto(Long newsId) {
        this.newsId = newsId;
    }

    public CommentDto(Long newsId, Long commentId) {
        this.newsId = newsId;
    }

    public CommentDto(Long newsId, Integer statusId) {
        this.newsId = newsId;
        this.statusId = statusId;
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return createdAt.format(formatter);
    }

    public String getFormattedUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return updatedAt.format(formatter);
    }
}
