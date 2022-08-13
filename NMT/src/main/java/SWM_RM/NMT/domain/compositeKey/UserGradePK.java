package SWM_RM.NMT.domain.compositeKey;

import SWM_RM.NMT.domain.User;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Embeddable
public class UserGradePK implements Serializable {
    @OneToOne
    private User user;
}
