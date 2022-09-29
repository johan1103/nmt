package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.UserGradeSheet;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProblemRepository {
    private final EntityManager em;

    /**
     * 문제를 생성할 때에는 문제 정보와 university엔티티, ProbType엔티티가 주어지면 연관관계 설정 후 영속상태로 설정한다.
     * @param problem
     * @param university
     * @param probType
     * @return Problem (Entity)
     */
    public Problem createProblem(Problem problem,University university,ProbType probType){
        problem.setProbType(probType);
        problem.setUniversity(university);
        em.persist(problem);
        return problem;
    }

    public Problem findProblemById(Long problemId){
        return em.find(Problem.class,problemId);
    }

    public List<Problem> findProblems(String universityNameFilter, String typeNameFilter,
                                      Long yearFilter){
        System.out.println(universityNameFilter);
        System.out.println(typeNameFilter);
        return em.createQuery("select p from Problem p join fetch p.probType join fetch p.university" +
                " where p.probType.typeName =: typeName and p.university.universityName =: universityName" +
                        " and p.createYear =: year" ,Problem.class)
                .setParameter("typeName",typeNameFilter)
                .setParameter("universityName",universityNameFilter)
                .setParameter("year",yearFilter)
                .getResultList();
    }

    public HashSet<Problem> findUserProblemList(Long userId){
        List<UserGradeSheet> userGradeSheets = em.createQuery("select ug from UserGradeSheet ug join fetch " +
                "ug.problem join ug.user where ug.user.id =: userId", UserGradeSheet.class)
                .setParameter("userId",userId)
                .getResultList();
        HashSet<Problem> problems = new HashSet<Problem>();
        for(UserGradeSheet userGradeSheet : userGradeSheets){
            problems.add(userGradeSheet.getProblem());
        }
        return problems;
    }


}
