package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class UserGradePK implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
