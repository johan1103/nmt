package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Data
@Embeddable
public class UserUniversityPK implements Serializable {
    @ManyToOne
    private University university;
    @ManyToOne
    private User user;
}
