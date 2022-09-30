package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    /**
     * 문제 목록 페이지에서 유저가 선택하기 위해 보여주는 선택지를 return하는 메서드
     * @return
     */
    public List<University> findAllUniversity(){
        return em.createQuery("select u from University u",University.class)
                .getResultList();
    }
}
