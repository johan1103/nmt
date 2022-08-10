package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.DbTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional //데이터를 변경해야하기 때문에 추가(?)
public class HibernateTest {
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void JpqlTest() throws Exception {
        DbTest dbTest = new DbTest();
        dbTest.setAge(10);
        dbTest.setName("Kim");
        em.persist(dbTest);
        TypedQuery<DbTest> = em.createQuery("select d from DbTest d",DbTest.class);
        /*
        List<DbTest> dbTestList = em.createQuery("select d from DbTest d",DbTest.class)
                        .getResultList();

         */
        System.out.println("hello");
    }
}