package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProbTypeRepository {
    private final EntityManager em;

    public Long createProbType(ProbType probType){
        em.persist(probType);
        return em.find(ProbType.class,probType.getId()).getId();
    }
}
