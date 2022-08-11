package SWM_RM.NMT.domain.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TMember2 {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @ManyToOne
    private TTeam2 tTeam2;
}
