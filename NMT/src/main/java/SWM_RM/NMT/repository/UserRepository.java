package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;
    private final UserGradeRepository userGradeRepository;
    public User findUser(Long userId){
        return em.find(User.class,userId);
    }
    public Long createUser(UserDTO userDTO){
        User user = new User();
        user.setNickName(userDTO.getNickName());
        user.setStrick(userDTO.getStrick());
        em.persist(user);
        //User 생성과 동시에 userGrade 또한 생성해서 연관관계 형성
        Long userGradeId = userGradeRepository.createUserGrade(user.getId());
        UserGrade userGrade = em.find(UserGrade.class,userGradeId);
        user.setUserGrade(userGrade);
        return user.getId();
    }
}
