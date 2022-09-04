package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.service.ProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProblemRepositoryTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProbTypeRepository probTypeRepository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @Test
    @Rollback(value = false)
    public void createProblemTest(){
        University university = new University();
        university.setUniversityName("Inha");
        ProbType probType = new ProbType();
        probType.setTypeName("Impl");
        Problem problem = new Problem();
        problem.setUniversity(university);
        problem.setProbType(probType);
        problem.setProbTitle("problem 1 in Inha");
        problem.setCreateYear(1999L);
        em.persist(problem);

        Problem problem2 = new Problem();
        problem2.setUniversity(university);
        problem2.setProbType(probType);
        problem2.setProbTitle("problem 2 in Inha");
        problem2.setCreateYear(1999L);
        em.persist(problem2);

    }

    @Test
    @Rollback(value = false)
    public void findProblemTest(){
        University university = new University();
        ProbType probType = new ProbType();
        university.setUniversityName("Inha");
        probType.setTypeName("Impl");
        List<Problem> list = problemService.findList(university.getUniversityName(),probType.getTypeName(),1999L);
        System.out.println("------------------");
        for(Problem p : list){
            System.out.println("p : "+p.getProbTitle());
        }
        System.out.println("------------------");
    }

}