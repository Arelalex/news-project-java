package db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortalUserFilter {
    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private Integer limit;
    private Integer offset;

}
