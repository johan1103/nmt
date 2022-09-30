package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProbTypeRepository {
    private final EntityManager em;

    /**
     * 문제 유형 생성 메서드
     * @param probType
     * @return ProbType (Entity)
     */
    public ProbType createProbType(ProbType probType){
        em.persist(probType);
        return probType;
    }
    /**
     * ProblemRepository에 인자로 ProbType을 주기 위해서 ProbType을 찾는 메서드
     * @param name
     * @return ProbType (Entity)
     */
    public ProbType findProbTypeByName(String name){
        return em.createQuery("select pt from ProbType pt where pt.typeName=:name"
        ,ProbType.class).setParameter("name",name)
                .getSingleResult();
    }

    /**
     * 문제 목록 페이지에서 유저가 선택하기 위해 보여주는 선택지를 return하는 메서드
     * @return
     */

    public List<ProbType> findAllProbType(){
        return em.createQuery("select pt from ProbType pt",ProbType.class)
                .getResultList();
    }
}
