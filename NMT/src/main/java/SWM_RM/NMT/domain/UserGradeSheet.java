package SWM_RM.NMT.domain;

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

}
