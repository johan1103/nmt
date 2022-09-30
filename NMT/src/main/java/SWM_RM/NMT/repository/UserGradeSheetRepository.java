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

    /**
     * 연관관계의 problem, user가 주어지고, service layer에서 채점이 된 정보와 유저가 작성한 정보들이 들어간 userGradeSheet이 넘어왔을때
     * 이들의 연관관계를 설정하고 영속상태로 설정
     * @param problem
     * @param user
     * @param userGradeSheet
     * @return
     */
    public UserGradeSheet createGradeSheet(Problem problem, User user, UserGradeSheet userGradeSheet){
        userGradeSheet.setUser(user);
        userGradeSheet.setProblem(problem);
        em.persist(userGradeSheet);
        return userGradeSheet;
    }

    public List<UserGradeSheet> findUserGradeSheetListByUserId(Long userId){
        return em.createQuery("select ugs from UserGradeSheet ugs " +
                "where ugs.user.id =: id",UserGradeSheet.class)
                .setParameter("id",userId).getResultList();
    }




}
