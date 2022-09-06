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
        return em.createQuery("select gs from UserGradeSheet gs join fetch gs.problem" +
                " where gs.problem.id =: probId",UserGradeSheet.class)
                .setParameter("probId",problemId)
                .getResultList();
    }
    public UserAverageGradeDTO findUniversityAverageGrade(Long universityId){
        //밑의 코드처럼 JPQL로 학교를 희망하는 학샐들만 가져오는 쿼리문 가져오기





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

    public UserAverageGradeDTO updateUserAverageGrade(Long userId){
        UserGrade userGrade = em.find(User.class,userId).getUserGrade();
        User findUser = em.find(User.class,userId);
        List<UserGradeSheet> userGradeSheets = em.createQuery("select ug from UserGradeSheet ug join " +
                "ug.user where ug.user.id =: userId",UserGradeSheet.class)
                .setParameter("userId",userId)
                .getResultList();
        Double grade1Everage = 0D;
        Double grade2Everage = 0D;
        Double grade3Everage = 0D;
        Double grade4Everage = 0D;
        Double grade5Everage = 0D;
        Double totalEverage = 0D;
        for(UserGradeSheet userGradeSheet : userGradeSheets){
            grade1Everage += userGradeSheet.getGrade1();
            grade2Everage += userGradeSheet.getGrade2();
            grade3Everage += userGradeSheet.getGrade3();
            grade4Everage += userGradeSheet.getGrade4();
            grade5Everage += userGradeSheet.getGrade5();
            totalEverage += userGradeSheet.getTotalEverage();
        }
        if(userGradeSheets.size()!=0) {
            grade1Everage /= userGradeSheets.size();
            grade2Everage /= userGradeSheets.size();
            grade3Everage /= userGradeSheets.size();
            grade4Everage /= userGradeSheets.size();
            grade5Everage /= userGradeSheets.size();
            totalEverage /= userGradeSheets.size();
        }
        UserAverageGradeDTO userAverageGradeDTO = new UserAverageGradeDTO();
        userAverageGradeDTO.setGrade1Everage(grade1Everage);
        userAverageGradeDTO.setGrade2Everage(grade2Everage);
        userAverageGradeDTO.setGrade3Everage(grade3Everage);
        userAverageGradeDTO.setGrade4Everage(grade4Everage);
        userAverageGradeDTO.setGrade5Everage(grade5Everage);
        userAverageGradeDTO.setTotalEverage(totalEverage);
        return userAverageGradeDTO;
    }

}
