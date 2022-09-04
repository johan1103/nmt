package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UniversityRepository {
    private final EntityManager em;

    public Long createUniversity(University university){
        em.persist(university);
        return em.find(University.class,university.getId()).getId();
    }
}