package SWM_RM.NMT.service;


import SWM_RM.NMT.data.CreateProblemDatas2;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.dto.ProblemListDTO;
import SWM_RM.NMT.repository.ProbTypeRepository;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UniversityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProblemServiceTest {

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProbTypeRepository probTypeRepository;
    @Autowired
    private UniversityRepository universityRepository;


    @Autowired
    private ProblemService problemService;

    @Test
    public void problemListTest(){
        CreateProblemDatas2 createProblemDatas2 = new CreateProblemDatas2(problemRepository,probTypeRepository,
                universityRepository);

        createProblemDatas2.createDatas();
        List<ProblemListDTO> findAllproblems = problemService.problemListService();
        for(ProblemListDTO p : findAllproblems){
            System.out.println(p.getProbTitle()+" "+p.getProbTitle());
        }
    }

    @Test
    public void problemListFilterService(){
        CreateProblemDatas2 createProblemDatas2 = new CreateProblemDatas2(problemRepository,probTypeRepository,
                universityRepository);
        createProblemDatas2.createDatas();

        List<Problem> findProblemListFilter = problemService.problemListFilterService(
                "요약",
                "고려대학교",
                2022L);
        System.out.println("---------세 필터 적용-----------");
        for(Problem p : findProblemListFilter){
            System.out.println(p.getProbTitle()+" "+p.getUniversity().getUniversityName()+
                    " "+p.getProbType().getTypeName());
        }

        List<Problem> findProblemListFilter1 = problemService.problemListFilterService(
                "요약",null,null
        );
        System.out.println("---------문제유형(요약) 필터 적용-----------");
        for(Problem p : findProblemListFilter1){
            System.out.println(p.getProbTitle()+" "+p.getUniversity().getUniversityName()+
                    " "+p.getProbType().getTypeName());
        }

        List<Problem> findProblemListFilter2 = problemService.problemListFilterService(
                null,"인하대학교",null
        );
        System.out.println("---------대학이름(인하대학교) 필터 적용-----------");
        for(Problem p : findProblemListFilter2){
            System.out.println(p.getProbTitle()+" "+p.getUniversity().getUniversityName()+
                    " "+p.getProbType().getTypeName());
        }

        List<Problem> findProblemListFilter3 = problemService.problemListFilterService(
                null,null,2022L
        );
        System.out.println("---------출제년도 필터 적용-----------");
        for(Problem p : findProblemListFilter3){
            System.out.println(p.getCreateYear()+" "+p.getProbTitle()+" "+p.getUniversity().getUniversityName()+
                    " "+p.getProbType().getTypeName());
        }
    }
}
