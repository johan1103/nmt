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

    /**
     * UserGrade를 update하기 위해서 한 User에 대한 모든 성적표들을 return하는 메서드
     * @param userId
     * @return
     */
    public List<UserGradeSheet> findUserGradeSheetListByUserId(Long userId){
        return em.createQuery("select ugs from UserGradeSheet ugs " +
                "where ugs.user.id =: id",UserGradeSheet.class)
                .setParameter("id",userId).getResultList();
    }

    /**
     * 특정 유저가 푼 문제들을 가져오기 위해 우선적으로 푼 문제집들을 문제의 정보와 같이 join fetch 로 가져오는
     * 메서드
     * @param userId
     * @return
     */
    public List<UserGradeSheet> findUserGradeSheetListByUserIdJoinFetchProblem(Long userId){
        return em.createQuery("select ugs from UserGradeSheet ugs  join fetch ugs.problem " +
                        "where ugs.user.id =: id",UserGradeSheet.class)
                .setParameter("id",userId).getResultList();
    }

    /**
     * 한 문제에 대한 성적표들을 전부 조회하기 위해서 한 문제에 대한 모든 성적표들을 return하는 메서드
     * @param problemId
     * @return
     */
    public List<UserGradeSheet> findUserGradeSheetListByProblemId(Long problemId){
        return em.createQuery("select ugs from UserGradeSheet ugs " +
                "where ugs.problem.id =: id",UserGradeSheet.class)
                .setParameter("id",problemId).getResultList();
    }

    /**
     * 한 문제에서 특정 유저의 성적표들만 조회해서 return하는 메서드
     * @param userId
     * @param problemId
     * @return
     */
    public List<UserGradeSheet> findUserGradeSheetListByUserIdProblemId(Long userId,Long problemId){
        return em.createQuery("select ugs from UserGradeSheet ugs " +
                "where ugs.user.id =: userId and ugs.problem.id =: problemId",UserGradeSheet.class)
                .setParameter("userId",userId).setParameter("problemId",problemId)
                .getResultList();
    }



}
