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
public class CreateNewsDto {
    String title;
    String description;
    String content;
    Part image;
    String userId;
    String categoryId;
}
