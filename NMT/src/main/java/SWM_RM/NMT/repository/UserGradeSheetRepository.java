package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.domain.dto.ScoreSet;
import SWM_RM.NMT.domain.dto.UserAverageGradeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public List<UserGradeSheet> findUserGradeSheetListByProblemRepository(Long problemId){
        //현재는 유저들의 모든 성적표임, 추후에 각 유저당 제일 좋은 성적표 하나만 산출해서 통계를 내도록 작성해야함.
        return em.createQuery("select gs from UserGradeSheet gs join fetch gs.problem" +
                " where gs.problem.id =: probId",UserGradeSheet.class)
                .setParameter("probId",problemId)
                .getResultList();
    }
    public UserAverageGradeDTO findUniversityAverageGrade(Long universityId){
        //밑의 코드처럼 JPQL로 학교를 희망하는 학샐들만 가져오는 쿼리문 가져오기
        /*
        List<UserGradeSheet> userGradeSheets = em.createQuery("select ug from UserGradeSheet ug" +
                " join ug.user.userUniversities")
                */
        TypedQuery<UserGradeSheet> query;

        //임의로 만든거
        UserAverageGradeDTO userAverageGradeDTO = new UserAverageGradeDTO();
        Double grade1Everage = 0D;
        Double grade2Everage = 0D;
        Double grade3Everage = 0D;
        Double grade4Everage = 0D;
        Double grade5Everage = 0D;
        Double totalEverage = 0D;
        userAverageGradeDTO.setGrade1Everage(grade1Everage);
        userAverageGradeDTO.setGrade2Everage(grade2Everage);
        userAverageGradeDTO.setGrade3Everage(grade3Everage);
        userAverageGradeDTO.setGrade4Everage(grade4Everage);
        userAverageGradeDTO.setGrade5Everage(grade5Everage);
        userAverageGradeDTO.setTotalEverage(totalEverage);
        return userAverageGradeDTO;
    }


}
