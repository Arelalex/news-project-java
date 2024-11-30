package db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PortalUserFilter {
    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private int limit;
    private int offset;

}
