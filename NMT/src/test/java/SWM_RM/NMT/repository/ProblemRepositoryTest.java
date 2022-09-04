package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
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
        em.persist(problem);
        System.out.println(problem.getId());
    }

    @Test
    @Rollback(value = false)
    public void findProblemTest(){
        University university = new University();
        ProbType probType = new ProbType();
        university.setUniversityName("Inha");
        probType.setTypeName("Impl");
        List<Problem> list = problemRepository.findProblems(university,probType,1999L);
        System.out.println("------------------");
        for(Problem p : list){
            System.out.println("p : "+p.getProbTitle());
        }
        System.out.println("------------------");
    }

}