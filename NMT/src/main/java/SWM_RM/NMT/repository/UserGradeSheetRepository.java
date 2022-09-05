package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.domain.dto.ScoreSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class UserGradeSheetRepository {
    private final EntityManager em;

    public Long createGradeSheet(Problem problem, User user, ScoreSet scoreSet, String text){
        LocalDateTime localDateTime = LocalDateTime.now();
        UserGradeSheet userGradeSheet = new UserGradeSheet();
        userGradeSheet.setUser(user);
        userGradeSheet.setGrade1(scoreSet.getGrade1());
        userGradeSheet.setGrade2(scoreSet.getGrade2());
        userGradeSheet.setGrade3(scoreSet.getGrade3());
        userGradeSheet.setGrade4(scoreSet.getGrade4());
        userGradeSheet.setGrade5(scoreSet.getGrade5());
        userGradeSheet.setGrade(scoreSet.getGrade());
        userGradeSheet.setProblem(problem);
        userGradeSheet.setTextSize(text.length());
        userGradeSheet.setReportText(text);
        userGradeSheet.setCreateTime(localDateTime);

        em.persist(userGradeSheet);
        return em.find(UserGradeSheet.class,userGradeSheet.getId()).getId();
    }

}
