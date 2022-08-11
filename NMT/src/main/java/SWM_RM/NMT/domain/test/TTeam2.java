package SWM_RM.NMT.domain.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class TTeam2 {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "tTeam2")
    private List<TMember2> tMember2List;
    @OneToMany(mappedBy = "tTeam2")
    private List<TMember3> tMember3List;
    @ManyToOne
    private TGroup tGroup;
}
