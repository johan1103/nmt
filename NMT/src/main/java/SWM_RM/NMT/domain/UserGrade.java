package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.compositeKey.UserGradePK;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserGrade {
    @EmbeddedId
    private UserGradePK userGradeId = new UserGradePK();
    @MapsId("userId")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user = new User();
    @Column
    private Double totalEverage;
    @Column
    private Double grade1Everage;
    @Column
    private Double grade2Everage;
    @Column
    private Double grade3Everage;
    @Column
    private Double grade4Everage;
    @Column
    private Double grade5Everage;
}
