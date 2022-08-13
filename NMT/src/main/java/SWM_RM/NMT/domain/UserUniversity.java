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
    private UserUniversityPK universityId = new UserUniversityPK();
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = new User();
    @MapsId("universityId")
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university = new University();
    @Column
    private Boolean interest;
}
