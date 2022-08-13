package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserGrade {
    @Id @JoinColumn @OneToOne
    private User user;
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
