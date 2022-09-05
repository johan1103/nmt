package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UniversityProbTypeRepository {
    private final EntityManager em;
    public List<ProbType> findUniversityProbTypeList(University university){
        return em.createQuery("select up.universityProbTypeId.probType from UniversityProbType up join up.universityProbTypeId.probType pt" +
                " where up.universityProbTypeId.university.universityName =: univName and up.interest = TRUE ",ProbType.class)
                .setParameter("univName",university.getUniversityName())
                .getResultList();
    }
}
