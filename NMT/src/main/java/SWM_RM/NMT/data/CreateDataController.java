package SWM_RM.NMT.data;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.repository.ProbTypeRepository;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UniversityRepository;
import SWM_RM.NMT.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@ResponseBody
@Transactional
@RequiredArgsConstructor
public class CreateDataController {
    private final ProblemRepository problemRepository;
    private final UniversityRepository universityRepository;
    private final ProbTypeRepository probTypeRepository;
    private final UserRepository userRepository;
    @GetMapping("create-data")
    public String createDataController(){
        CreateProblemDatas2 createProblemDatas2 = new CreateProblemDatas2(problemRepository,probTypeRepository
        ,universityRepository);
        createProblemDatas2.createDatas();
        return "ok";
    }
    @GetMapping("create-data2")
    public String createData2Controller(){
        CreateUserDatas createUserDatas = new CreateUserDatas(userRepository);
        createUserDatas.createDatas();
        return "ok";
    }
    @GetMapping("create-problem")
    public String createDataProblem(){
        University university = universityRepository.findUniversityByName("한국외국어대학교");
        ProbType probType = probTypeRepository.findProbTypeByName("요약");
        Problem problem = new Problem();
        problem.setCreateYear(2022L);
        problem.setCompetetionRate(24.02);
        problem.setProbTitle("2022학년도 한국외국어대학교 논술고사");
        problem.setBestText("/template/bestTextList/bestText19");
        problem.setSolvedNum(0L);
        problem.setRecommendedSubmissionSize(350L);
        problem.setProbText("/template/problemList/problem19");
        problemRepository.createProblem(problem,university,probType);
        return "ok";
    }
}
