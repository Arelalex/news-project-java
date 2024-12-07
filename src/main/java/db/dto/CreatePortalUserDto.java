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
public class CreatePortalUserDto {
    String firstName;
    String lastName;
    String nickname;
    String email;
    String password;
    String image;
    Roles role;

}
