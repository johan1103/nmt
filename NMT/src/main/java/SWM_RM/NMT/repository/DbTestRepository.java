package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.DbTest;
import SWM_RM.NMT.domain.dto.DbTestDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DbTestRepository {
    @PersistenceContext
    EntityManager em;
    public List<DbTest> getDbList(){
        return em.createQuery("select d from DbTest as d",DbTest.class)
                .getResultList();
    }
    public String createDbTest(DbTestDTO dbTestDTO){
        DbTest dbTest = new DbTest();
        dbTest.setAge(dbTestDTO.getAge());
        dbTest.setName(dbTestDTO.getName());
        em.persist(dbTest);
        DbTest returnDb = em.find(DbTest.class,dbTest.getId());
        return returnDb.getName();
    }
}
