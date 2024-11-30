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

    private Long id;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String attachment;
    private NewsEntity news;
    private PortalUserEntity user;
    private StatusEntity status;

    public CommentEntity(String content, LocalDateTime createAt, LocalDateTime updateAt, String attachment, NewsEntity news, PortalUserEntity user, StatusEntity status) {
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.attachment = attachment;
        this.news = news;
        this.user = user;
        this.status = status;
    }

}
