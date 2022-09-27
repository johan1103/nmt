package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String nickName;
    private String strick;
    private String email;
    private Role role;
}
