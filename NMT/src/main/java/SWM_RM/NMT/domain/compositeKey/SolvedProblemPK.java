package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.Problem;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SolvedProblemPK implements Serializable {
    private Long problemId;
}
