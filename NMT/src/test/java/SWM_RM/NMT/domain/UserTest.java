package SWM_RM.NMT.domain;

import SWM_RM.NMT.domain.compositeKey.UserUniversityPK;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private EntityManager em;
    @Test
    @Transactional
    @Rollback(value = false)
    public void createUserTest() throws Exception {
        User user = new User();
        user.setNickName("hi");
        user.setStrick("1234");
        em.persist(user);
    }
}
