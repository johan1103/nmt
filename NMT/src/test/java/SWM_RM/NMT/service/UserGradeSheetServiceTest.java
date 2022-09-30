package SWM_RM.NMT.service;

import SWM_RM.NMT.data.CreateProblemDatas2;
import SWM_RM.NMT.data.CreateUserDatas;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.repository.ProbTypeRepository;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UniversityRepository;
import SWM_RM.NMT.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserGradeSheetServiceTest {

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProbTypeRepository probTypeRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGradeSheetService userGradeSheetService;

    @Test
    @Rollback(value = false)
    public void submitUserGradeSheetService(){
        /**
         * 사전 데이터 생성
         */
        CreateProblemDatas2 createProblemDatas2 = new CreateProblemDatas2(problemRepository,probTypeRepository,
                universityRepository);
        CreateUserDatas userDatas = new CreateUserDatas(userRepository);
        createProblemDatas2.createDatas();
        userDatas.createDatas();

        /**
         * 제출할 유저, 제출할 문제, 제출할 텍스트 지정
         */
        Problem submitProblem = problemRepository.findProblemList().get(0);
        User user = userRepository.findUserList().get(0);
        String reportText = "제출문장. 가나다라마바사아자차카타파하";

        userGradeSheetService.submitUserGradeSheetService(reportText,user.getId(),submitProblem.getId());
        userGradeSheetService.submitUserGradeSheetService(reportText+" 2",
                user.getId(), submitProblem.getId());
    }
}
