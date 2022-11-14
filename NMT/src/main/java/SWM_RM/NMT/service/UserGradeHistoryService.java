package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.UserGradeHistory;
import SWM_RM.NMT.domain.UserGradeSheet;
import SWM_RM.NMT.domain.dto.ProblemListDTO;
import SWM_RM.NMT.repository.UserGradeHistoryRepository;
import SWM_RM.NMT.repository.UserGradeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserGradeHistoryService {

    /**
     * 특정 User가 자신의 History를 조회하고자 할때 해당 정보를 return하는 메서드
     * @param userId
     * @return
     */
    private final UserGradeHistoryRepository userGradeHistoryRepository;
    private final UserGradeSheetRepository userGradeSheetRepository;
    public List<UserGradeHistory> userGradeHistoryService(Long userId){
        return userGradeHistoryRepository.findUserGradeHistoryByUserId(userId);
    }

    public List<ProblemListDTO> userSolvedProblemListService(Long userId){
        HashSet<Problem> problemHashSet= new HashSet<Problem>();
        List<ProblemListDTO> problems = new ArrayList<>();
        List<UserGradeSheet> userGradeSheetList = userGradeSheetRepository
                .findUserGradeSheetListByUserIdFetchJoinProblem(userId);
        for(UserGradeSheet ugh : userGradeSheetList){
            problemHashSet.add(ugh.getProblem());
        }
        for(Problem p : problemHashSet){
            problems.add(ProblemListDTO.problemListDtoConverter(p));
        }
        return problems;
    }

    /**
     * user Strick return
     * @param userId
     * @return
     */
    public HashMap<Integer,Integer> userGradeHistoryStrickService(Long userId){
        List<UserGradeSheet> userGradeHistories = userGradeSheetRepository
                .findUserGradeSheetListByUserId(userId);
        HashMap<Integer,Integer> strick = new HashMap<Integer,Integer>();
        for(int i=1;i<=12;i++){
            strick.put(i,0);
        }
        for(UserGradeSheet ugs : userGradeHistories){
            Integer month=ugs.getCreateTime().getMonth().getValue();
            System.out.println("month is "+month+" plus value "+(strick.get(month)+1));
            strick.put(month,strick.get(month)+1);
        }
        return strick;
    }

    public HashMap<Integer,Double> userTotalGradeHistoryServcie(long userId){
        List<UserGradeSheet> userGradeSheetList=userGradeSheetRepository
                .findUserGradeSheetListByUserId(userId);
        HashMap<Integer,Double> totalGradeStrick=new HashMap<>();
        List<Integer> totalList=new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0));
        for(int i=1;i<=12;i++){
            totalGradeStrick.put(i,0D);
        }
        for(UserGradeSheet ugs:userGradeSheetList){
            Integer month=ugs.getCreateTime().getMonth().getValue();
            totalList.set(month,totalList.get(month)+1);
            totalGradeStrick.put(month,totalGradeStrick.get(month)+ugs.getTotalEverage());
        }
        for(int i=1;i<=12;i++){
            if(totalList.get(i)!=0) {
                Double avg=totalGradeStrick.get(i)/totalList.get(i);
                totalGradeStrick.put(i, (Math.round(avg * 100)/100.0));
            }
            System.out.println("month "+i+", value "+totalGradeStrick.get(i));
        }
        return totalGradeStrick;
    }

    public HashMap<Integer,Double> userSpecificGradeHistoryService(Long userId,Long gradeNum){
        List<UserGradeSheet> userGradeSheetList=userGradeSheetRepository
                .findUserGradeSheetListByUserId(userId);
        HashMap<Integer,Double> gradeStrick=new HashMap<>();
        List<Integer> totalList=new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0));
        for(int i=1;i<=12;i++){
            gradeStrick.put(i,0D);
        }
        for(UserGradeSheet ugs:userGradeSheetList){
            Integer month=ugs.getCreateTime().getMonth().getValue();
            totalList.set(month,totalList.get(month)+1);
            if(gradeNum==1)
                gradeStrick.put(month,gradeStrick.get(month)+ugs.getGrade1());
            else if(gradeNum==2)
                gradeStrick.put(month,gradeStrick.get(month)+ugs.getGrade2());
            else
                gradeStrick.put(month,gradeStrick.get(month)+ugs.getGrade3());
        }
        for(int i=1;i<=12;i++){
            if(totalList.get(i)!=0) {
                Double avg=gradeStrick.get(i)/totalList.get(i);
                gradeStrick.put(i, (Math.round(avg * 100)/100.0));
            }
            //System.out.println("month "+i+", value "+gradeStrick.get(i));
        }
        return gradeStrick;
    }
}
