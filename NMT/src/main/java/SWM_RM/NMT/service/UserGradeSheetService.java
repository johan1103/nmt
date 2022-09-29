package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.domain.dto.ScoreSet;
import SWM_RM.NMT.domain.dto.UserAverageGradeDTO;
import SWM_RM.NMT.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGradeSheetService {
    private final UserGradeSheetRepository userGradeSheetRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final ScoreSetService scoreSetService;
    private final UserGradeRepository userGradeRepository;


    /**
     * (이 주석은 service 구현때 참고해서 service주석 작성후 삭제할 것.)
     * service layer에서 해야 할 것.
     *      1. 문제 제출 정보 토대로 userGradeSheet내부 내용 작성
     *      2. 제출한 유저 확인해서 유저 정보와 problem 정보 가져와서 userGradeSheet의 Create메서드로 전달
     *      3. (userGradeSheet Create이후)해당 유저 평균 성적표 업데이트 와 성적표 히스토리 create로직 작성
     */
    public void createGradeSheetService(Long userId, Long problemId, String text){
        /*
        LocalDateTime localDateTime = LocalDateTime.now();
        UserGradeSheet userGradeSheet = new UserGradeSheet();
        userGradeSheet.setUser(user);
        userGradeSheet.setGrade1(scoreSet.getGrade1());
        userGradeSheet.setGrade2(scoreSet.getGrade2());
        userGradeSheet.setGrade3(scoreSet.getGrade3());
        userGradeSheet.setGrade4(scoreSet.getGrade4());
        userGradeSheet.setGrade5(scoreSet.getGrade5());
        userGradeSheet.setProblem(problem);
        userGradeSheet.setTextSize(text.length());
        userGradeSheet.setReportText(text);
        userGradeSheet.setCreateTime(localDateTime);
        */
        return;
    }

    public UserGradeSheet findUserGradeSheetService(Long userGradeSheetId){
        return userGradeSheetRepository.findUserGradeSheetById(userGradeSheetId);
    }

    public List<UserGradeSheet> findUserGradeSheetListByProblemService(Long problemId){
        return userGradeSheetRepository.findUserGradeSheetListByProblemRepository(problemId);
    }

}
