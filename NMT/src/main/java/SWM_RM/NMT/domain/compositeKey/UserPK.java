package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Embeddable
public class UserPK implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
