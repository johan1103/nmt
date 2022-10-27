package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGradeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    /**
     * 특정 User가 자신의 History를 조회하고자 할때 해당 정보를 return하는 메서드
     * @param userId
     * @return
     */
    public List<UserGradeHistory> findUserGradeHistoryByUserId(Long userId){
        return em.createQuery("select ugh from UserGradeHistory ugh where " +
                "ugh.user.id =: id",UserGradeHistory.class)
                .setParameter("id",userId).getResultList();
    }
}
