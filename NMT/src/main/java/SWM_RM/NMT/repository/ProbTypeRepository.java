package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProbTypeRepository {
    private final EntityManager em;

    public Long createProbType(String probTypeName){
        ProbType probType = new ProbType();
        probType.setTypeName(probTypeName);
        em.persist(probType);
        return em.find(ProbType.class,probType.getId()).getId();
    }
}
