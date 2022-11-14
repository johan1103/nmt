package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Problem {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String probText;
    @Column
    private String probTitle;
    @Column
    private String bestText;
    @Column(columnDefinition = "LONGTEXT")
    private String probExp;
    @Column
    private Long createYear;
    @Column
    private Double competetionRate;
    @Column
    private Long recommendedSubmissionSize;
    @Column
    private Long solvedNum;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private University university;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProbType probType;
}
