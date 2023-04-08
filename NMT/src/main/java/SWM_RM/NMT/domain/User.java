package SWM_RM.NMT.domain;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.compositeKey.UserUniversityPK;
import SWM_RM.NMT.domain.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Table(name = "Users")
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String nickName;
    @Column
    private String strick;
    @Column(unique = true)
    private String oauthKey;
    @OneToOne
    private UserGrade userGrade;
    @Column
    private Double exp;

    protected User(String nickName, String oauthKey){
        this.nickName=nickName;
        this.oauthKey=oauthKey;
        this.strick="empty";
        this.exp=0D;
    }
    public static User create(String nickName, String oauthKey){
        return new User(nickName,oauthKey);
    }
    public void initUserGrade(UserGrade userGrade){
        this.userGrade=userGrade;
    }
}
