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
import java.util.List;

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
        //여기에 점수 업데이트 로직 추가해야함 (어떻게 할 지 모르겠음)

        em.persist(userGradeSheet);
        return em.find(UserGradeSheet.class,userGradeSheet.getId()).getId();
    }

    public UserGradeSheet findUserGradeSheetById(Long userGradeSheetId){
        return em.find(UserGradeSheet.class,userGradeSheetId);
    }
    public List<UserGradeSheet> findUserGradeSheetListByProblemService(Long problemId){
        return em.createQuery("select gs from UserGradeSheet gs join fetch gs.problem" +
                " where gs.problem.id =: probId",UserGradeSheet.class)
                .setParameter("probId",problemId)
                .getResultList();
    }

}
