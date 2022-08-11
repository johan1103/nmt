package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.DbTest;
import SWM_RM.NMT.domain.test.*;
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
    @Rollback(value = true)
    public void JpqlTest() throws Exception {
        DbTest dbTest = new DbTest();
        dbTest.setAge(10);
        dbTest.setName("Kim");
        em.persist(dbTest);
        //TypedQuery<DbTest> testTypedQuery= em.createQuery("select d from DbTest d",DbTest.class);

        List<DbTest> dbTestList = em.createQuery("select d from DbTest d",DbTest.class)
                        .getResultList();
    }
    @Test
    @Rollback(value = false)
    public void multiBagException() throws Exception{
        setField();

    }
    @Test
    @Rollback(value = false)
    public void N1Test() throws Exception{
        TTeam tTeam1 = new TTeam();
        tTeam1.setName("TeamA");
        TTeam tTeam2 = new TTeam();
        tTeam2.setName("TeamB");
        TMember tMember1 = new TMember();
        tMember1.setName("A1");
        tMember1.setTTeam(tTeam1);
        TMember tMember2 = new TMember();
        tMember2.setName("A2");
        tMember2.setTTeam(tTeam1);
        TMember tMember3 = new TMember();
        tMember3.setName("B1");
        tMember3.setTTeam(tTeam2);
        em.persist(tTeam1);
        em.persist(tTeam2);
        em.persist(tMember1);
        em.persist(tMember2);
        em.persist(tMember3);
        em.flush();
        em.clear();
        List<TMember> list = em.createQuery("select m from TMember m join fetch m.tTeam",TMember.class)
                .getResultList();
        for(TMember tMember : list){
            System.out.println("member "+tMember.getName()+"'s tema : "+tMember.getTTeam().getName());
        }
    }
    static void setField(){
        TTeam2 t2 = new TTeam2();
        TMember2 m2 = new TMember2();
        TMember3 m3 = new TMember3();
        TGroup g = new TGroup();
        t2.setName("t2");
        m2.setName("m2");
        m3.setName("m3");
        m2.setTTeam2(t2);
        m3.setTTeam2(t2);
        t2.setTGroup(g);
    }
}