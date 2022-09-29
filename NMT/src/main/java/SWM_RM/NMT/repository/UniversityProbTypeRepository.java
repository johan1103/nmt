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

    /**
     *  조회된 UniversityProbType이 없을 때 해당 엔티티 생성 메서드.
     * @param universityProbType
     * @param university
     * @param probType
     * @return UniversityProbType
     */
    public UniversityProbType createUniversityProbType(UniversityProbType universityProbType,
                                                         University university, ProbType probType){
        UniversityProbTypePK universityProbTypePK = new UniversityProbTypePK();
        universityProbTypePK.setProbType(probType);
        universityProbTypePK.setUniversity(university);
        universityProbType.setUniversityProbTypeId(universityProbTypePK);
        universityProbType.setInterest(Boolean.TRUE);
        em.persist(universityProbType);
        return universityProbType;
    }
}
