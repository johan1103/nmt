package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.University;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UniversityProbTypePK implements Serializable {
    private Long probTypeId;
    private Long universityId;
}
