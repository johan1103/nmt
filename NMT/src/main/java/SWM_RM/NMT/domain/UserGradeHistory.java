package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class UserGradeHistory {
    @Id @GeneratedValue
    private Long id;
    @Column
    private LocalDateTime updateTime;
    @ManyToOne
    private User user;
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
    private Double totalGrade;

    public void setUserGradeByUserGradeSheet(UserGradeSheet userGradeSheet){
        this.grade1=userGradeSheet.getGrade1();
        this.grade2= userGradeSheet.getGrade2();
        this.grade3= userGradeSheet.getGrade3();
        this.grade4= userGradeSheet.getGrade4();
        this.grade5= userGradeSheet.getGrade5();
        this.totalGrade= userGradeSheet.getTotalEverage();
    }
}
