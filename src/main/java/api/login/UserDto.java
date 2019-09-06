package api.login;

import api.config.UserType;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String email;
    private String username;
    private String password;
    private String name;
    private String surname;
    private UserType type;
}
