package SWM_RM.NMT.createTest;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.*;
import SWM_RM.NMT.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create Source (Entity)
 * UserUniversity UniversityProbType
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserUniversityCreateTest {

    @Autowired
    private UserUniversityRepository userUniversityRepository;
    @Autowired
    private UniversityProbTypeRepository universityProbTypeRepository;

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProbTypeRepository probTypeRepository;

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

    @Test
    @Rollback(value = false)
    public void universityProbTypeCreateTest(){
        University university = new University();
        university.setUniversityName("univ2");
        universityRepository.createUniversity(university);
        ProbType probType = new ProbType();
        probType.setTypeName("probType1");
        probTypeRepository.createProbType(probType);

        universityProbTypeRepository.createUniversityProbType(new UniversityProbType(),university,probType);
    }
}
