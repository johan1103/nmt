package SWM_RM.NMT.createTest;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.repository.ProbTypeRepository;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UniversityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * Create Source (Entity)
 * Problem ProbType University
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProblemCreateTest {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProbTypeRepository probTypeRepository;
    @Autowired
    private UniversityRepository universityRepository;

    /**
     * DB구현, 혹은 em 사용이 아닌, 메모리 리포지토리 사용 이유
     * 서비스 단에서는 Problem 을 만들 때, 사전에 Problem과 연관관계를 가지는
     * University와 ProbType이 주어져야 하는데, 이를 한번에 구현해서 테스트하려니 너무 코드가 길어진다.
     *
     * 각각 따로 생성을 테스트하고, 서비스 단에서 정상적으로 로직이 작동 되었다는 가정하에 Create 메서드를 테스트 하는 것이므로
     * 서비스에서 할 역할을 memoryRepository에서 그 역할을 대신 하는것.
     */
    private HashMap<String,ProbType> memoryProbtypeRepository = new HashMap<>();
    private HashMap<String,University> memoryUniversityRepository = new HashMap<>();

    @Test
    @Rollback(value = false)
    public void probTypeCreateTest(){
        //요약', '비교․ 대조', '설명․ 해설', '분석 유형이 존재
        ProbType probType1 = new ProbType();
        probType1.setTypeName("요약");
        ProbType probType2 = new ProbType();
        probType2.setTypeName("비교. 대조");
        ProbType probType3 = new ProbType();
        probType3.setTypeName("설명. 해설");
        ProbType probType4 = new ProbType();
        probType4.setTypeName("분석");

        probTypeRepository.createProbType(probType1);
        probTypeRepository.createProbType(probType2);
        probTypeRepository.createProbType(probType3);
        probTypeRepository.createProbType(probType4);

        //다른 테스트 메서드에서 사용하기 위해 메모리에 저장
        memoryProbtypeRepository.put(probType1.getTypeName(),probType1);
        memoryProbtypeRepository.put(probType2.getTypeName(),probType2);
        memoryProbtypeRepository.put(probType3.getTypeName(),probType3);
        memoryProbtypeRepository.put(probType4.getTypeName(),probType4);

    }
    @Test
    @Rollback(value = false)
    public void universityCreateTest(){
        //대학교는 서울시립대, 고려대학교, 성균관대학교, 경희대학고, 인하대학교가 존재

        University university1 = new University();
        university1.setUniversityName("고려대학교");
        University university2 = new University();
        university2.setUniversityName("서울시립대학교");
        University university3 = new University();
        university3.setUniversityName("성균관대학교");
        University university4 = new University();
        university4.setUniversityName("경희대학교");
        University university5 = new University();
        university5.setUniversityName("인하대학교");

        universityRepository.createUniversity(university1);
        universityRepository.createUniversity(university2);
        universityRepository.createUniversity(university3);
        universityRepository.createUniversity(university4);
        universityRepository.createUniversity(university5);

        //다른 메서드에서 사용하기 위해 메모리에 저장
        memoryUniversityRepository.put(university1.getUniversityName(),university1);
        memoryUniversityRepository.put(university2.getUniversityName(),university2);
        memoryUniversityRepository.put(university3.getUniversityName(),university3);
        memoryUniversityRepository.put(university4.getUniversityName(),university4);
        memoryUniversityRepository.put(university5.getUniversityName(),university5);


        Problem problem1 = setProblemAuto("problem1");
        problemRepository.createProblem(problem1,university1,
                memoryProbtypeRepository.get("요약"));
    }


    @Test
    @Rollback(value = false)
    public void problemCreateTest(){
        //문제는 총 7개가 존재
        /*
        Problem problem1 = setProblemAuto("problem1");
        Problem problem2 = setProblemAuto("problem2");
        Problem problem3 = setProblemAuto("problem3");
        Problem problem4 = setProblemAuto("problem4");
        Problem problem5 = setProblemAuto("problem5");
        Problem problem6 = setProblemAuto("problem6");
        Problem problem7 = setProblemAuto("problem7");
        Problem problem8 = setProblemAuto("problem8");
        Problem problem9 = setProblemAuto("problem9");
        Problem problem10 = setProblemAuto("problem10");

        problemRepository.createProblem(problem1,memoryUniversityRepository.get("고려대학교"),
                memoryProbtypeRepository.get("요약"));
        problemRepository.createProblem(problem2,memoryUniversityRepository.get("서울시립대학교"),
                memoryProbtypeRepository.get("비교. 대조"));
        problemRepository.createProblem(problem3,memoryUniversityRepository.get("성균관대학교"),
                memoryProbtypeRepository.get("설명. 해설"));
        problemRepository.createProblem(problem4,memoryUniversityRepository.get("경희대학교"),
                memoryProbtypeRepository.get("분석"));
        problemRepository.createProblem(problem5,memoryUniversityRepository.get("인하대학교"),
                memoryProbtypeRepository.get("요약"));
        problemRepository.createProblem(problem6,memoryUniversityRepository.get("고려대학교"),
                memoryProbtypeRepository.get("비교. 대조"));
        problemRepository.createProblem(problem7,memoryUniversityRepository.get("서울시립대학교"),
                memoryProbtypeRepository.get("설명. 해설"));
        problemRepository.createProblem(problem8,memoryUniversityRepository.get("성균관대학교"),
                memoryProbtypeRepository.get("분석"));
        problemRepository.createProblem(problem9,memoryUniversityRepository.get("경희대학교"),
                memoryProbtypeRepository.get("요약"));
        problemRepository.createProblem(problem10,memoryUniversityRepository.get("인하대학교"),
                memoryProbtypeRepository.get("비교. 대조"));

         */
    }

    public static Problem setProblemAuto(String problemName){
        Problem problem = new Problem();
        problem.setCreateYear(2022L);
        problem.setProbExp(problemName+" Exp");
        problem.setBestText(problemName+" Best Text");
        problem.setProbText(problemName+" Prob Text");
        problem.setProbTitle(problemName);
        problem.setCompetetionRate(6.56D);
        return problem;
    }
}
