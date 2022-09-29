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

    public UserGradeHistory createUserGradeHistory(UserGradeHistory userGradeHistory, User user){
        userGradeHistory.setUser(user);
        em.persist(userGradeHistory);
        return userGradeHistory;
    }
}
