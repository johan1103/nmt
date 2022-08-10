package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserUniversity {
    @Id
    private Long id;
    @ManyToOne
    private University university;
    @ManyToOne
    private User user;
    @Column
    private Boolean interest;
}
