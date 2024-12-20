package db.dto;

import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCommentDto {
    String commentId;
    String content;
    Part attachment;
    String newsId;
    String userId;
    String statusId;

}
