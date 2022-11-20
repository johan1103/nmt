package SWM_RM.NMT.data;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.dto.admin.AdminUniversityDTO;
import SWM_RM.NMT.domain.dto.admin.CreateProblemDTO;
import SWM_RM.NMT.repository.ProbTypeRepository;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UniversityRepository;
import SWM_RM.NMT.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String createDataProblem(@RequestBody CreateProblemDTO problemDTO){
        University university = universityRepository.findUniversityByName(problemDTO.getUnivName());
        ProbType probType = probTypeRepository.findProbTypeByName(problemDTO.getProbTypeName());
        Problem problem = new Problem();
        problem.setCreateYear(problemDTO.getCreateYear());
        problem.setCompetetionRate(problemDTO.getCompetitionRate());
        problem.setProbTitle(problemDTO.getProbTitle());
        problem.setBestText(problemDTO.getProbBestTextPath());
        problem.setSolvedNum(0L);
        problem.setRecommendedSubmissionSize(problemDTO.getRecommendedTextSize());
        problem.setProbText(problemDTO.getProbTextPath());
        problem.setProbExp(problemDTO.getProbExp());
        problemRepository.createProblem(problem,university,probType);
        return "ok";
    }

    @GetMapping("update-problem")
    public String updateDataProblem(@RequestBody CreateProblemDTO problemDTO){
        Problem problem = problemRepository.findProblemById(problemDTO.getProblemId());
        if(problemDTO.getUnivName()!=null){
            University university = universityRepository.findUniversityByName(problemDTO.getUnivName());
            if(university!=null)
                problem.setUniversity(university);
        }
        if(problemDTO.getProbTypeName()!=null){
            ProbType probType = probTypeRepository.findProbTypeByName(problemDTO.getProbTypeName());
            if(probType!=null)
                problem.setProbType(probType);
        }
        if(problemDTO.getCreateYear()!=null)
            problem.setCreateYear(problemDTO.getCreateYear());
        if(problemDTO.getCompetitionRate()!=null)
            problem.setCompetetionRate(problemDTO.getCompetitionRate());
        if(problemDTO.getProbTitle()!=null)
            problem.setProbTitle(problemDTO.getProbTitle());
        if(problemDTO.getProbBestTextPath()!=null)
            problem.setBestText(problemDTO.getProbBestTextPath());
        if(problemDTO.getRecommendedTextSize()!=null)
            problem.setRecommendedSubmissionSize(problemDTO.getRecommendedTextSize());
        if(problemDTO.getProbTextPath()!=null)
            problem.setProbText(problemDTO.getProbTextPath());
        if(problemDTO.getProbExp()!=null)
            problem.setProbExp(problemDTO.getProbExp());
        return "ok";
    }

    @GetMapping("create-university")
    public String createUniversity(@RequestBody AdminUniversityDTO universityDTO){
        University university = new University();
        university.setUniversityName(universityDTO.getName());
        universityRepository.createUniversity(university);
        return "ok";
    }

    @GetMapping("update-university")
    public String updateUniversity(@RequestBody AdminUniversityDTO universityDTO){
        University university = universityRepository.findUniversityById(universityDTO.getId());
        university.setUniversityName(universityDTO.getName());
        return "ok";
    }
}
