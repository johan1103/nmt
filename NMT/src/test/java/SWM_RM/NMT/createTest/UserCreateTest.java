package SWM_RM.NMT.createTest;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create Source (Entity)
 * User UserGrade
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserCreateTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void userCreateTest() throws Exception {
        for(int i=1;i<=3;i++){
            // userRepository Input Mock data
            User user = new User();
            user.setStrick("abc");
            user.setNickName("user"+i);
            user.setRole(Role.USER);
            user.setEmail("user"+i+"@gmail.com");

            userRepository.createUser(user);
        }
    }
}
