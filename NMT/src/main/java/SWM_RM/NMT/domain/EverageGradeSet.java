package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class EverageGradeSet {
    @Id @GeneratedValue
    private Long id;
    @Column
    private Double everageGrade1;
    @Column
    private Double everageGrade2;
    @Column
    private Double everageGrade3;
    @Column
    private Double everageGrade4;
    @Column
    private Double everageGrade5;
    @Column
    private Double everageGrade;
}
