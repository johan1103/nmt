package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.compositeKey.UserUniversityPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserUniversity {
    @EmbeddedId
    private UserUniversityPK universityPK;
    @Column
    private Boolean interest;
}
