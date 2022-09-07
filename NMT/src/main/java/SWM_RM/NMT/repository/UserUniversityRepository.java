package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserUniversity;
import SWM_RM.NMT.domain.compositeKey.UserUniversityPK;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserUniversityRepository {
    private final EntityManager em;

    public UserUniversityPK createUserUniversity(User user, University university){
        UserUniversityPK userUniversityPK = new UserUniversityPK();
        userUniversityPK.setUniversity(university);
        userUniversityPK.setUser(user);
        UserUniversity userUniversity = new UserUniversity();
        userUniversity.setUniversityId(userUniversityPK);
        userUniversity.setInterest(Boolean.TRUE);
        em.persist(userUniversity);
        return userUniversity.getUniversityId();
    }
}
