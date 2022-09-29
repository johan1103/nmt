package SWM_RM.NMT.createTest;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserUniversity;
import SWM_RM.NMT.repository.UniversityRepository;
import SWM_RM.NMT.repository.UserRepository;
import SWM_RM.NMT.repository.UserUniversityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserUniversityTest {

    @Autowired
    private UserUniversityRepository userUniversityRepository;

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void userUniversityCreateTest(){
        User user = new User();
        user.setRole(Role.USER);
        user.setNickName("user1");
        userRepository.createUser(user);
        University university = new University();
        university.setUniversityName("univ1");
        universityRepository.createUniversity(university);

        userUniversityRepository.createUserUniversity(new UserUniversity(),user,university);
    }
}
