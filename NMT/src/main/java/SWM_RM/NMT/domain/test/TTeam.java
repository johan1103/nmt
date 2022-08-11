package SWM_RM.NMT.domain.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class TTeam {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "tTeam")
    private List<TMember> list;
}
