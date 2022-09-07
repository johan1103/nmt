package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.EverageGradeSet;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.domain.dto.ScoreSet;
import SWM_RM.NMT.domain.dto.UserAverageGradeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EverageGradeSetRepository {
    private final EntityManager em;
    public EverageGradeSet getEverageGradeRepository(Long gradeSetid){
        return em.find(EverageGradeSet.class,gradeSetid);
    }

}
