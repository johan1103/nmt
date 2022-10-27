package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.UserGradeHistory;
import SWM_RM.NMT.repository.UserGradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGradeHistoryService {

    /**
     * 특정 User가 자신의 History를 조회하고자 할때 해당 정보를 return하는 메서드
     * @param userId
     * @return
     */
    private final UserGradeHistoryRepository userGradeHistoryRepository;
    public List<UserGradeHistory> userGradeHistoryService(Long userId){
        return userGradeHistoryRepository.findUserGradeHistoryByUserId(userId);
    }

    /**
     * user Strick return
     * @param userId
     * @return
     */
    public HashMap<Integer,Integer> UserGradeHistroyStrickService(Long userId){
        List<UserGradeHistory> userGradeHistories = userGradeHistoryRepository
                .findUserGradeHistoryByUserId(userId);
        HashMap<Integer,Integer> strick = new HashMap<Integer,Integer>();
        for(int i=1;i<=12;i++){
            strick.put(i,0);
        }
        for(UserGradeHistory ugh : userGradeHistories){
            Integer month=ugh.getUpdateTime().getMonth().getValue();
            strick.put(month,strick.get(month)+1);
        }
        return strick;
    }
}
