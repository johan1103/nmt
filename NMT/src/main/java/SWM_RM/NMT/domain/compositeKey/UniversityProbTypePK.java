package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.University;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Data
@Embeddable
public class UniversityProbTypePK implements Serializable {
    @ManyToOne
    private ProbType probType;
    @ManyToOne
    private University university;
}
