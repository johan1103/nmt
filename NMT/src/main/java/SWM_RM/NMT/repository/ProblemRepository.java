package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProblemRepository {
    private final EntityManager em;

    public Long createProblem(Problem problem){
        em.persist(problem);
        Problem findProblem = em.find(Problem.class,problem.getId());
        return findProblem.getId();
    }

    public List<Problem> findProblems(University universityFilter, ProbType probTypeFilter,
                                      Long Year){
        String typeNameFilter = probTypeFilter.getTypeName();
        String universityNameFilter = universityFilter.getUniversityName();
        return em.createQuery("select p from Problem p join fetch ProbType pt join fetch University u" +
                " where pt.typeName =: typeNameFilter and u.universityName =: universityNameFilter",Problem.class)
                .getResultList();
    }


}
