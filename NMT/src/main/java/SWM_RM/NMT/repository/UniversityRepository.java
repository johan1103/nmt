package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UniversityRepository {
    private final EntityManager em;
    /**
     * University 생성 메서드
     * @param university
     * @return ProbType (Entity)
     */
    public University createUniversity(University university){
        em.persist(university);
        return university;
    }

    public University findUniversityByName(String name){
        return em.createQuery("select u from University u where u.universityName =: name",University.class)
                .setParameter("name",name)
                .getSingleResult();
    }
}
