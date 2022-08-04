package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column
    private String probExp;
    @Column
    private LocalDateTime CreateDate;
    @Column
    private Double CompetetionRate;
    @Column
    private String probType;
}
