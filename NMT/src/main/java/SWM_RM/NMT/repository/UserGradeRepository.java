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

}
