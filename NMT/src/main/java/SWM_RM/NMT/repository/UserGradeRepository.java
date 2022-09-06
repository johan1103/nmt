package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.UserGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserGradeRepository {
    private final EntityManager em;

    public Long createUserGrade(Long userId){
        UserGrade userGrade = new UserGrade();
        userGrade.setGrade1Everage(0D);
        userGrade.setGrade2Everage(0D);
        userGrade.setGrade3Everage(0D);
        userGrade.setGrade4Everage(0D);
        userGrade.setGrade5Everage(0D);
        userGrade.setTotalEverage(0D);
        em.persist(userGrade);
        return em.find(UserGrade.class,userGrade.getId()).getId();
    }
    public UserGrade findUserGrade(Long userId){
        return em.find(UserGrade.class,userId);
    }
}
