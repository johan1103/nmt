package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.EverageGradeSet;
import SWM_RM.NMT.domain.dto.ScoreSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class EverageGradeSetRepository {
    private final EntityManager em;

    public Long updateEverageGradeSet(ScoreSet scoreSet){
        return 1L;
    }
    public EverageGradeSet getEverageGradeSetRepository(Long gradeSetid){
        return em.find(EverageGradeSet.class,gradeSetid);
    }
}
