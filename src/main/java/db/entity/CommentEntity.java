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
public class CommentEntity {

    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String attachment;
    private NewsEntity news;
    private PortalUserEntity user;
    private StatusesEntity status;

    public CommentEntity(String content, LocalDateTime createAt, LocalDateTime updatedAt, String attachment, NewsEntity news, PortalUserEntity user, StatusesEntity status) {
        this.content = content;
        this.createdAt = createAt;
        this.updatedAt = updatedAt;
        this.attachment = attachment;
        this.news = news;
        this.user = user;
        this.status = status;
    }

}
