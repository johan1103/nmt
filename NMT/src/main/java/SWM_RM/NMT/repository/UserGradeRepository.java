package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.domain.dto.UserAverageGradeDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserGradeRepository {
    private final EntityManager em;

    /**
     * User를 생성하면서 동시에 UserGrade를 생성하므로 UserGrade는 빈 내용으로 생성되어야 한다.
     * @return UserGrade
     */
    public UserGrade createUserGrade(){
        UserGrade createUserGrade = new UserGrade();
        createUserGrade.setGrade1Everage(0D);
        createUserGrade.setGrade2Everage(0D);
        createUserGrade.setGrade3Everage(0D);
        createUserGrade.setGrade4Everage(0D);
        createUserGrade.setGrade5Everage(0D);
        createUserGrade.setTotalEverage(0D);
        em.persist(createUserGrade);
        return createUserGrade;
    }
    public UserGrade findUserGrade(Long userId){
        return em.find(UserGrade.class,userId);
    }


    public UserAverageGradeDTO updateUserAverageGrade(Long userId){
        //현재는 학생의 지금까지 풀었던 모든 성적표임, 추후에 한 문제에는 제일 좋은 성적표만 통계에 반영되도록 수정해야함.
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
        userGrade.setGrade1Everage(grade1Everage);
        userGrade.setGrade2Everage(grade2Everage);
        userGrade.setGrade3Everage(grade3Everage);
        userGrade.setGrade4Everage(grade4Everage);
        userGrade.setGrade5Everage(grade5Everage);
        userGrade.setTotalEverage(totalEverage);
        return userAverageGradeDTO;
    }
}
