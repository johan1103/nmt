package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UniversityProbType {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private University university;
    @ManyToOne
    private ProbType probType;
    @Column
    private Boolean interest;
}
