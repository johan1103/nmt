package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;
    private final UserGradeRepository userGradeRepository;

    /**
     * 유저를 생성할 때에는 유저의 인적 사항이 주어지면, 빈 유저 성적표를 생성해서 연관관계 설정 후, 영속상태로 설정한다.
     * @param user
     * @return User
     */
    public User createUser(User user){
        //User 생성과 동시에 userGrade 또한 생성해서 연관관계 형성
        UserGrade createUserGrade = userGradeRepository.createUserGrade();
        user.initUserGrade(createUserGrade);
        em.persist(user);
        return user;
    }

    /**
     * 성적표 생성시 연관관계 설정을 위해 유저 정보를 id로 찾아서 return해주는 메서드
     * @param userId
     * @return
     */
    public User findUserById(Long userId){
        return em.find(User.class,userId);
    }

    /**
     * 성적표 생성 테스트 코드에서 사용할 메서드 (아무 유저나 가져오기 위해서)
     * @return
     */
    public List<User> findUserList(){
        return em.createQuery("select u from User u",User.class).getResultList();
    }
    public List<User> findUserByKey(String key){
        return em.createQuery("select u from User u where u.nickName=:key",User.class)
                .setParameter("key",key).getResultList();
    }
}
