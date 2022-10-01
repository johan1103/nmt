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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class MyPageServiceTest {


    @PersistenceContext
    EntityManager em;
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
    public void userSolvedListServiceTest(){
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
        String reportText = "1번 문제, 1회차 제출";
        Problem submitProblem2 = problemRepository.findProblemList().get(1);

        userGradeSheetService.submitUserGradeSheetService(reportText,user.getId(),submitProblem.getId());
        userGradeSheetService.submitUserGradeSheetService("1번 문제, 2회차 제출",
                user.getId(), submitProblem.getId());
        userGradeSheetService.submitUserGradeSheetService("2번 문제, 1회차 제출",
                user.getId(),submitProblem2.getId());

        /**
         * N+1 문제 테스트 위해서 쓰기 지연 쿼리 전부 발송, 쿼리저장소 전부 비우기.
         */
        em.flush();
        em.clear();
        Long userId = user.getId();
        System.out.println("--------------------------사전 작업 종료-----------------------------------");

        List<Problem> problemList = userGradeSheetService.userSolvedListService(userId);

        System.out.println("--------------------------Service메서드 종료-----------------------------------");
        System.out.println(user.getNickName()+"'s Problem list");
        for(Problem p : problemList){
            System.out.println(p.getProbTitle());
        }
    }
}
