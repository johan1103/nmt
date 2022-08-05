package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String Nickname;
    @Column
    private String Strick;
    /*
    @OneToMany(mappedBy = "user")
    List<UserProblemSheet> userSheets;
    */
}
