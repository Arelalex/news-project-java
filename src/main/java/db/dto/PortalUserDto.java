package db.dto;

import db.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortalUserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;
    private Roles role;
    private Integer limit;
    private Integer offset;

}
