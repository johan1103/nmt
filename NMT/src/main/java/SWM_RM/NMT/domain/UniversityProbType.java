package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.compositeKey.UniversityProbTypePK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UniversityProbType {
    @EmbeddedId
    private UniversityProbTypePK universityProbTypeId = new UniversityProbTypePK();
    @MapsId("universityId")
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university= new University();

    @MapsId("probTypeId")
    @ManyToOne
    @JoinColumn(name = "probType_id")
    private ProbType probType = new ProbType();

    @Column
    private Boolean interest;
}
