package SWM_RM.NMT.domain;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.compositeKey.UserUniversityPK;
import SWM_RM.NMT.domain.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String nickName;
    @Column
    private String strick;
    @Column
    private String email;
    @OneToOne
    private UserGrade userGrade;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleKey() {
        return this.role.getKey();
    }
    public UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName(user.getNickName());
        userDTO.setStrick(user.getStrick());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
    /*
    @OneToMany(mappedBy = "user")
    List<UserProblemSheet> userSheets;
    */
}
