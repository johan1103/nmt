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
    private UniversityProbTypePK universityProbTypeId;
    @Column
    private Boolean interest;
}
