package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProbTypeRepository {
    private final EntityManager em;

    /**
     * 문제 유형 생성 메서드
     * @param probType
     * @return
     */
    public ProbType createProbType(ProbType probType){
        em.persist(probType);
        return probType;
    }

    /**
     * ProblemRepository에 인자로 ProbType을 주기 위해서 ProbType을 찾는 메서드
     * @param name
     * @return
     */
    public ProbType findProbTypeByName(String name){
        return em.createQuery("select pt from ProbType pt where pt.typeName=:name"
        ,ProbType.class).setParameter("name",name)
                .getSingleResult();
    }
}
