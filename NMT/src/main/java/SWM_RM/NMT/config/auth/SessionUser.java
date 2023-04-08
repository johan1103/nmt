package SWM_RM.NMT.config.auth;

import SWM_RM.NMT.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getNickName();
        this.email = user.getOauthKey();
    }
}
