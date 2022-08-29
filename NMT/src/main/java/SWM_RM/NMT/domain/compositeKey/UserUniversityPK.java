package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class UserUniversityPK implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private University university;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
