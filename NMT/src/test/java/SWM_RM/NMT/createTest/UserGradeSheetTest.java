package SWM_RM.NMT.createTest;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGradeHistory;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserGradeSheetTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserGradeSheetRepository userGradeSheetRepository;

    @Autowired
    private UserGradeHistoryRepository userGradeHistoryRepository;

    /**
     * Create Source (Entity)
     * UserGradeSheet UserGradeHistory
     */
    @Test
    @Rollback(value = false)
    public void userGradeSheetCreateTest(){
        User user = new User();
        user.setNickName("user1");
        user.setRole(Role.USER);
        //원래는 애초에 있어야 하지만 테스트이므로 지금 생성
        userRepository.createUser(user);
        Problem problem = new Problem();
        problem.setProbTitle("prob1");
        problemRepository.createProblem(problem,null,null);
        UserGradeSheet userGradeSheet = new UserGradeSheet();
        userGradeSheet.setGrade1(1D);
        userGradeSheet.setGrade2(1D);
        userGradeSheet.setGrade3(1D);
        userGradeSheet.setGrade4(1D);
        userGradeSheet.setGrade5(1D);
        userGradeSheet.setCreateTime(LocalDateTime.now());
        userGradeSheet.setTextSize(1);
        userGradeSheet.setReportText("reportText");
        userGradeSheet.setTotalEverage(1D);

        userGradeSheetRepository.createGradeSheet(problem,user,userGradeSheet);
    }

    @Test
    @Rollback(value = false)
    public void userGradeHistoryCreateTest(){
        User user = new User();
        user.setNickName("user2");
        user.setRole(Role.USER);
        userRepository.createUser(user);
        UserGradeHistory userGradeHistory = new UserGradeHistory();
        userGradeHistory.setGrade1(1D);
        userGradeHistory.setGrade2(1D);
        userGradeHistory.setGrade3(1D);
        userGradeHistory.setGrade4(1D);
        userGradeHistory.setGrade5(1D);
        userGradeHistory.setTotalGrade(1D);
        userGradeHistory.setUpdateTime(LocalDateTime.now());


        userGradeHistoryRepository.createUserGradeHistory(userGradeHistory,user);
    }

}
