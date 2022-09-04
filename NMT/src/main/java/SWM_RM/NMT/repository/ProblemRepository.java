package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
                                      Long year){
        System.out.println("Repository UnivName "+universityFilter.getUniversityName());
        String typeNameFilter = probTypeFilter.getTypeName();
        String universityNameFilter = universityFilter.getUniversityName();
        System.out.println(universityNameFilter);
        System.out.println(typeNameFilter);
        String sen = new String();
        sen = "problem 1 in Inha";
        return em.createQuery("select p from Problem p join fetch p.probType join fetch p.university" +
                " where p.probType.typeName =: typeName and p.university.universityName =: universityName",Problem.class)
                .setParameter("typeName",typeNameFilter)
                .setParameter("universityName",universityNameFilter)
                .getResultList();

        /*
        return em.createQuery("select p from Problem p join fetch p.probType join fetch p.university" +
                        " where p.probTitle =: senParam",Problem.class)
                .setParameter("senParam",sen)
                .getResultList();
         */
    }


}
