package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGradeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserGradeHistoryRepository {

    private final EntityManager em;
    /**
     * History 생성 메서드
     * @param userGradeHistory, user
     * @return UserGradeHistory (Entity)
     */
    public UserGradeHistory createUserGradeHistory(UserGradeHistory userGradeHistory, User user){
        userGradeHistory.setUser(user);
        em.persist(userGradeHistory);
        return userGradeHistory;
    }
}
