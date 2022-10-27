package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.UserGradeHistory;
import SWM_RM.NMT.repository.UserGradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        List<UserGradeHistory> userGradeHistories = userGradeHistoryRepository
                .findUserGradeHistoryByUserId(userId);

        /**
         * 히스토리 보여주는 로직을 어떻게 짤 것인가....
         */


        return userGradeHistoryRepository.findUserGradeHistoryByUserId(userId);
    }
}
