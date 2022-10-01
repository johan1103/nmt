package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.*;
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

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final UserGradeSheetRepository userGradeSheetRepository;
    private final UserGradeHistoryRepository userGradeHistoryRepository;

    /**
     * (이 주석은 service 구현때 참고해서 service주석 작성후 삭제할 것.)
     * service layer에서 해야 할 것.
     *      1. 문제 제출 정보 토대로 userGradeSheet내부 내용 작성
     *      2. 제출한 유저 확인해서 유저 정보와 problem 정보 가져와서 userGradeSheet의 Create메서드로 전달
     *      3. (userGradeSheet Create이후)해당 유저 평균 성적표 업데이트 와 성적표 히스토리 create로직 작성
     */
    public UserGradeSheet submitUserGradeSheetService(String reportText,Long userId,Long problemId){
        /**
         * userGradeSheet 채점 및 생성
         */
        UserGradeSheet userGradeSheet = new UserGradeSheet();
        userGradeSheet.setReportText(reportText);
        userGradeSheet.setCreateTime(LocalDateTime.now());
        userGradeSheet.setTextSize(reportText.length());

        User findUser = userRepository.findUserById(userId);
        Problem findProblem = problemRepository.findProblemById(problemId);

        ScoreSet gradedScoreSet = tempGradeEvaluater(reportText);
        userGradeSheet.setGradesByScoreSet(gradedScoreSet);

        userGradeSheetRepository.createGradeSheet(findProblem,findUser,userGradeSheet);


        /**
         *UserGrade 호출 및 업데이트
         */
        UserGrade userGrade = findUser.getUserGrade();
        List<UserGradeSheet> userGradeSheetList = userGradeSheetRepository.findUserGradeSheetListByUserId(userId);
        Double updateGrade1 = 0D;
        Double updateGrade2 = 0D;
        Double updateGrade3 = 0D;
        Double updateGrade4 = 0D;
        Double updateGrade5 = 0D;
        Double updateTotalGrade = 0D;
        for(UserGradeSheet ugs : userGradeSheetList){
            updateGrade1+=ugs.getGrade1();
            updateGrade2+=ugs.getGrade2();
            updateGrade3+=ugs.getGrade3();
            updateGrade4+=ugs.getGrade4();
            updateGrade5+=ugs.getGrade5();
            updateTotalGrade+=ugs.getTotalEverage();
        }
        int listSize=userGradeSheetList.size();
        updateGrade1/=listSize;
        updateGrade2/=listSize;
        updateGrade3/=listSize;
        updateGrade4/=listSize;
        updateGrade5/=listSize;
        updateTotalGrade/=listSize;

        userGrade.setGrade1Everage(updateGrade1);
        userGrade.setGrade2Everage(updateGrade2);
        userGrade.setGrade3Everage(updateGrade3);
        userGrade.setGrade4Everage(updateGrade4);
        userGrade.setGrade5Everage(updateGrade5);
        userGrade.setTotalEverage(updateTotalGrade);

        /**
         * 성적표 히스토리 Create
         */
        UserGradeHistory userGradeHistory = new UserGradeHistory();
        userGradeHistory.setUserGradeByUserGradeSheet(userGradeSheet);
        userGradeHistory.setUpdateTime(LocalDateTime.now());
        userGradeHistoryRepository.createUserGradeHistory(userGradeHistory,findUser);

        return userGradeSheet;
    }

    /**
     * 한 문제에 대한 모든 성적표 조회해서 return하는 메서드
     * @param problemId
     * @return
     */
    public List<UserGradeSheet> gradeSheetListService(Long problemId){
        return  userGradeSheetRepository.findUserGradeSheetListByProblemId(problemId);
    }

    /**
     * 한 문제에서 특정 유저의 성적표들만 조회해서 return하는 메서드
     * @param userId
     * @param problemId
     * @return
     */

    public List<UserGradeSheet> userGradeSheetListService(Long userId,Long problemId){
        return userGradeSheetRepository.findUserGradeSheetListByUserIdProblemId(userId,problemId);
    }

    /**
     * 임시 임의 채점기
     * @param text
     * @return
     */
    public static ScoreSet tempGradeEvaluater(String text){
        ScoreSet scoreSet = new ScoreSet();
        scoreSet.setGrade1(Math.ceil((Math.random()*10000)%5));
        scoreSet.setGrade2(Math.ceil((Math.random()*10000)%5));
        scoreSet.setGrade3(Math.ceil((Math.random()*10000)%5));
        scoreSet.setGrade4(Math.ceil((Math.random()*10000)%5));
        scoreSet.setGrade5(Math.ceil((Math.random()*10000)%5));

        Double sum = 0D;
        sum+=scoreSet.getGrade1();
        sum+=scoreSet.getGrade2();
        sum+=scoreSet.getGrade3();
        sum+=scoreSet.getGrade4();
        sum+=scoreSet.getGrade5();

        scoreSet.setTotalEverage(sum/5);

        scoreSet.setGrade("B");
        return scoreSet;
    }



}
