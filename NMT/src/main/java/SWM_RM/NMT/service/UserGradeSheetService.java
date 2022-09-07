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

    public Long createGradeSheetService(Long userId, Long problemId, String text){
        User user = userRepository.findUser(userId);
        Problem problem = problemRepository.findProblemById(problemId);
        ScoreSet scoreSet = scoreSetService.gradingScore();
        Long gradeSheetId = userGradeSheetRepository.createGradeSheet(problem, user, scoreSet, text);
        //유저 평균 점수 업데이트 함수
        UserAverageGradeDTO userAverageGradeDTO = userGradeRepository.updateUserAverageGrade(userId);
        return gradeSheetId;
    }

    public UserGradeSheet findUserGradeSheetService(Long userGradeSheetId){
        return userGradeSheetRepository.findUserGradeSheetById(userGradeSheetId);
    }

    public List<UserGradeSheet> findUserGradeSheetListByProblemService(Long problemId){
        return userGradeSheetRepository.findUserGradeSheetListByProblemRepository(problemId);
    }

}
