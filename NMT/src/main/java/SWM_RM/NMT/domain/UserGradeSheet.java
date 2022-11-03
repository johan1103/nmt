package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.dto.ScoreSet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserGradeSheet {
    @Id @GeneratedValue
    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String reportText;
    @Column
    private Integer textSize;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Problem problem;
    @Column
    private LocalDateTime createTime;

    @Column
    private Double totalEverage;
    @Column
    private Double grade1;
    @Column
    private Double grade2;
    @Column
    private Double grade3;
    @Column
    private Double grade4;
    @Column
    private Double grade5;
    @Column
    private String grade;

    public void setGradesByScoreSet(ScoreSet scoreSet){
        this.totalEverage= scoreSet.getTotalEverage();
        this.grade1=scoreSet.getGrade1();
        this.grade2=scoreSet.getGrade2();
        this.grade3=scoreSet.getGrade3();
        this.grade4=scoreSet.getGrade4();
        this.grade5=scoreSet.getGrade5();
        this.grade=scoreSet.getGrade();
    }

}
