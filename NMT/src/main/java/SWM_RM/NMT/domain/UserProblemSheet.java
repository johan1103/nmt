package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserProblemSheet {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String reportText;
    @Column
    private String grade;
    @Column
    private Integer textSize;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Problem problem;
}
