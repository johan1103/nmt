package SWM_RM.NMT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class University {
    @Id @GeneratedValue
    @Column(name = "university_id")
    private Long id;
    @Column
    private String universityName;
}
