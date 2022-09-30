package SWM_RM.NMT.data;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.repository.UserRepository;

public class CreateUserDatas {

    public CreateUserDatas(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    private final UserRepository userRepository;

    public void createDatas() {
        for (int i = 1; i <= 3; i++) {
            // userRepository Input Mock data
            User user = new User();
            user.setStrick("abc");
            user.setNickName("user" + i);
            user.setRole(Role.USER);
            user.setEmail("user" + i + "@gmail.com");
            userRepository.createUser(user);
        }
    }
}
