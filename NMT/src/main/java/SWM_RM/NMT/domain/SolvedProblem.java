package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.compositeKey.SolvedProblemPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SolvedProblem {
    @EmbeddedId
    private SolvedProblemPK solvedProblemId = new SolvedProblemPK();

    @MapsId("problemId")
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column
    private Long solvedNum;
}
