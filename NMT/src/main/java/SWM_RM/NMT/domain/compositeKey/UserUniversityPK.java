package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserUniversityPK implements Serializable {
    private University universityId;
    private User userId;
}
