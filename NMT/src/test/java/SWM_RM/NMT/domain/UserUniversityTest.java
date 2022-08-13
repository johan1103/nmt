package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.compositeKey.UserUniversityPK;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserUniversityTest {
    @Autowired
    private EntityManager em;
    @Test
    @Transactional
    @Rollback(value = false)
    public void createUserUniversity() throws Exception {
        User user = new User();
        University university = new University();
        UserUniversity userUniversity = new UserUniversity();
        UserUniversityPK userUniversityPK = new UserUniversityPK();
        user.setNickName("kimmm");
        user.setStrick("12111");
        university.setUniversityName("InhaUniv");
        userUniversityPK.setUniversity(university);
        userUniversityPK.setUser(user);
        userUniversity.setUniversityId(userUniversityPK);
        userUniversity.setInterest(true);
        em.persist(user);
        em.persist(university);
        em.persist(userUniversity);

    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void userTest() throws Exception {
        User user = new User();
        user.setStrick("134");
        user.setNickName("hello");
        em.persist(user);
    }
}
