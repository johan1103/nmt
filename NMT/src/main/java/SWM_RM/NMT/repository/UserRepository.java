package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;
    public User findUser(Long userId){
        return em.find(User.class,userId);
    }
}
