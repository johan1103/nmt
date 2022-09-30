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

    /**
     * 모든 문제를 조회하고자 할 때, 현재 존재하는 모든 문제들을 return하는 메서드
     * @return
     */
    public List<Problem> findProblemList(){
        return em.createQuery("select p from Problem p",Problem.class).getResultList();
    }

    /**
     * 문제 풀이를 위한 페이지를 들어갈 때, ProblemId가 주어지면 해당 하는 문제의 상세 정보들을 return하는 메서드
     * @param problemId
     * @return
     */
    public Problem findProblemById(Long problemId){
        return em.find(Problem.class,problemId);
    }

    /**
     * 현재는 필터기능이 인자 경우의 수에 따라서 유연하지 못함. 일단은 일일히 인자의 경우의 수마다 메서드 정의
     * @param universityNameFilter
     * @param typeNameFilter
     * @param yearFilter
     * @return
     */
    public List<Problem> findProblemListFilter(String universityNameFilter, String typeNameFilter,
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

    public List<Problem> findProblemListOneFilter1(String universityNameFilter){
        System.out.println(universityNameFilter);
        return em.createQuery("select p from Problem p join fetch p.university" +
                " where p.university.universityName =: name",Problem.class)
                .setParameter("name",universityNameFilter).getResultList();
    }

    public List<Problem> findProblemListOneFilter2(String typeNameFilter){
        System.out.println(typeNameFilter);
        return em.createQuery("select p from Problem p join fetch p.probType" +
                " where p.probType.typeName =: name",Problem.class)
                .setParameter("name",typeNameFilter).getResultList();
    }
    public List<Problem> findProblemListOneFilter3(Long createYear){
        System.out.println(createYear);
        return em.createQuery("select p from Problem p where p.createYear =: year",Problem.class)
                .setParameter("year",createYear).getResultList();
    }
    //여기까지 필터


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
