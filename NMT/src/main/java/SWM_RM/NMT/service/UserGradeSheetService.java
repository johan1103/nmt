package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.*;
import SWM_RM.NMT.domain.dto.GradeSheetDetailDTO;
import SWM_RM.NMT.domain.dto.GradeSheetListDTO;
import SWM_RM.NMT.domain.dto.ScoreSet;
import SWM_RM.NMT.domain.dto.UserAverageGradeDTO;
import SWM_RM.NMT.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGradeSheetService {

    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final UserGradeSheetRepository userGradeSheetRepository;
    private final UserGradeHistoryRepository userGradeHistoryRepository;

    /**
     * 특정 성적표를 조회하고자 할 때 성적표 정보를 return하는 메서드
     * @param userGradeSheetId
     * @return
     */
    public GradeSheetDetailDTO userGradeSheetService(Long userGradeSheetId){
        UserGradeSheet userGradeSheet = userGradeSheetRepository
                .findUserGradeSheetByUserGradeSheetId(userGradeSheetId);
        GradeSheetDetailDTO gradeSheetDetailDTO = GradeSheetDetailDTO
                .gradeSheetDetailDtoConverter(userGradeSheet,
                        userGradeSheet.getProblem(),userGradeSheet.getUser());
        return gradeSheetDetailDTO;
    }

    /**
     * 타 유저의 성적표를 볼 때 자신의 것도 비교 분석하기 위해 자신이 푼 가장 총점이 높은 성적표를 return하는 메서드
     * @param userId
     * @param userGradeSheetId
     * @return
     */
    public UserGradeSheet userGradeSheetCompareService(Long userId,Long userGradeSheetId){
        /**
         * 성적표를 조회하려는 페이지는 필히 userGradeSheetId를 주게 되어있고, problemId는 주지 않을 것 같으므로, 직접 problemId를 찾아야함.
         */
        UserGradeSheet userGradeSheet = userGradeSheetRepository.findUserGradeSheetByUserGradeSheetId(userGradeSheetId);
        Problem problem = userGradeSheet.getProblem();
        /**
         * 찾은 problem과 인자로 받은 userId로, user가 푼 문제를 가져온 후, 가장 총점이 우수한 UserGradeSheet을 가져옴.
         * 쿼리문이 속도가 훨씬 빠르니, 이걸 자바 로직이 아닌, 쿼리문으로 해결하는 것도 좋을 듯
         */
        List<UserGradeSheet> userGradeSheetList = userGradeSheetRepository.
                findUserGradeSheetListByUserIdProblemId(userId, problem.getId());
        UserGradeSheet bestUserGradeSheet = null;
        Double bestTotal=0D;
        for(UserGradeSheet u : userGradeSheetList){
            if(u.getTotalEverage()>bestTotal){
                bestTotal=u.getTotalEverage();
                bestUserGradeSheet=u;
            }
        }
        return bestUserGradeSheet;
    }

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
        System.out.println("-------find user------");
        Problem findProblem = problemRepository.findProblemById(problemId);
        System.out.println("-------find Problem------");

        ScoreSet gradedScoreSet = tempGradeEvaluater(reportText);
        userGradeSheet.setGradesByScoreSet(gradedScoreSet);

        userGradeSheetRepository.createGradeSheet(findProblem,findUser,userGradeSheet);
        System.out.println("-------create gradeSheet------");

        /**
         * user 경험치 상승
         */
        //findUser.setExp(findUser.getExp()+gradedScoreSet.getTotalEverage());


        /**
         *UserGrade 호출 및 업데이트
         */
        UserGrade userGrade = findUser.getUserGrade();
        List<UserGradeSheet> userGradeSheetList = userGradeSheetRepository.findUserGradeSheetListByUserId(userId);
        System.out.println("-------find userGradeSheet------");
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
        System.out.println("-------create userGradeHistory------");

        return userGradeSheet;
    }

    /**
     * 한 문제에 대한 모든 성적표 조회해서 return하는 메서드
     * 
     * @param problemId
     * @return List<GradeSheetListDTO>
     */
    public List<GradeSheetListDTO> gradeSheetListService(Long problemId){
        List<UserGradeSheet> userGradeSheetList =
                userGradeSheetRepository.findUserGradeSheetListByProblemId(problemId);
        List<GradeSheetListDTO> gradeSheetListDTO = new ArrayList<>();
        System.out.println("---------------get grade sheet list, start converting");
        for(UserGradeSheet userGradeSheet : userGradeSheetList){
            gradeSheetListDTO.add(GradeSheetListDTO.gradeSheetDtoConverter(userGradeSheet));
        }
        return  gradeSheetListDTO;
    }

    /**
     * 한 문제에서 특정 유저의 성적표들만 조회해서 return하는 메서드
     * @param userId
     * @param problemId
     * @return
     */

    public List<GradeSheetListDTO> userGradeSheetListService(Long userId,Long problemId){
        List<UserGradeSheet> userGradeSheetList =
                userGradeSheetRepository.findUserGradeSheetListByUserIdProblemId(userId,problemId);
        List<GradeSheetListDTO> gradeSheetListDTO = new ArrayList<>();
        System.out.println("---------------get grade sheet list, start converting");
        for(UserGradeSheet userGradeSheet : userGradeSheetList){
            gradeSheetListDTO.add(GradeSheetListDTO.gradeSheetDtoConverter(userGradeSheet));
        }
        return gradeSheetListDTO;
    }

    /**
     * 마이페이지에서 유저가 푼 문제들을 보고자 할 때 문제 리스트들을 return해주는 메서드
     * @param userId
     * @return
     */
    public List<Problem> userSolvedListService(Long userId){
        List<UserGradeSheet> userGradeSheetList=userGradeSheetRepository.
                findUserGradeSheetListByUserIdJoinFetchProblem(userId);
        List<Problem> problemList = new ArrayList<>();
        HashSet<Problem> problemHashSet = new HashSet<>();
        for(UserGradeSheet ugs : userGradeSheetList){
            System.out.println("-------------- user problem "+ugs.getProblem().getProbTitle());
            problemHashSet.add(ugs.getProblem());
        }
        Iterator<Problem> iter = problemHashSet.iterator();
        while (iter.hasNext()){
            problemList.add(iter.next());
        }
        return problemList;
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
        try {
            System.out.println("Sleep 3s: "  + LocalDateTime.now());
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return scoreSet;
    }



}
