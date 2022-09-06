package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.UniversityProbType;
import SWM_RM.NMT.domain.compositeKey.UniversityProbTypePK;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UniversityProbTypeRepository {
    private final EntityManager em;
    public List<ProbType> findUniversityProbTypeList(String universityName){
        return em.createQuery("select up.universityProbTypeId.probType from UniversityProbType up join up.universityProbTypeId.probType pt" +
                " where up.universityProbTypeId.university.universityName =: univName and up.interest = TRUE ",ProbType.class)
                .setParameter("univName",universityName)
                .getResultList();
    }
    public UniversityProbTypePK createUniversityProbType(University university, ProbType probType){
        UniversityProbType universityProbType = new UniversityProbType();
        UniversityProbTypePK universityProbTypePK = new UniversityProbTypePK();
        universityProbTypePK.setProbType(probType);
        universityProbTypePK.setUniversity(university);
        universityProbType.setUniversityProbTypeId(universityProbTypePK);
        universityProbType.setInterest(Boolean.TRUE);
        em.persist(universityProbType);
        return universityProbTypePK;
    }
}
