package SWM_RM.NMT.domain;

import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DbTest {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    private String name;
    private Integer age;
}
