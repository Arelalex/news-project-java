package db.entity;

import db.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalUserEntity {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;
    private String image;
    private Roles role;
    // private List<News> news;

    public PortalUserEntity(String firstName, String lastName, String nickname, String email, String password, String image, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
    }

}
