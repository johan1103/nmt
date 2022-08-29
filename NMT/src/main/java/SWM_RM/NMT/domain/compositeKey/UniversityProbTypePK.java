package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.University;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class UniversityProbTypePK implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private ProbType probType;
    @ManyToOne(fetch = FetchType.LAZY)
    private University university;
}
