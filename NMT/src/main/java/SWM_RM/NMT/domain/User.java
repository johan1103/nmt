package SWM_RM.NMT.domain;

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
    @OneToOne
    private UserGrade userGrade;
    /*
    @OneToMany(mappedBy = "user")
    List<UserProblemSheet> userSheets;
    */
}
