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
    private Integer userSolvedNum;
    //private Role role;

    public static UserDTO userDtoConverter(User user,Integer solvedNum){
        UserDTO userDTO = new UserDTO();
        userDTO.setStrick(user.getStrick());
        userDTO.setEmail(user.getEmail());
        userDTO.setNickName(user.getNickName());
        userDTO.setUserSolvedNum(solvedNum);
        return userDTO;
    }
}
