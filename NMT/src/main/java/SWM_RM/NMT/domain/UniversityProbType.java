package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class UniversityProbType {
    @Id @ManyToOne
    private University university;
    @Id @ManyToOne
    private ProbType probType;
    @Column
    private Boolean interest;
}
