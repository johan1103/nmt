package SWM_RM.NMT.data;

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
}