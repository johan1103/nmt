package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserUniversity {
    @Id @ManyToOne
    private University university;
    @Id @ManyToOne
    private User user;
    @Column
    private Boolean interest;
}
