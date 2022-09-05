package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.dto.ScoreSet;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UserGradeSheetRepository;
import SWM_RM.NMT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserGradeSheetService {
    private final UserGradeSheetRepository userGradeSheetRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final ScoreSetService scoreSetService;

    public Long createGradeSheetService(Long userId, Long problemId, String text){
        User user = userRepository.findUser(userId);
        Problem problem = problemRepository.findProblemById(problemId);
        ScoreSet scoreSet = scoreSetService.gradingScore();
        Long gradeSheetId = userGradeSheetRepository.createGradeSheet(problem, user, scoreSet, text);
        return gradeSheetId;
    }
}
