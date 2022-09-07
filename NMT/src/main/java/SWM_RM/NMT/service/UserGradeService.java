package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.domain.dto.UserAverageGradeDTO;
import SWM_RM.NMT.repository.UserGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGradeService {
    private final UserGradeRepository userGradeRepository;

    public UserGrade findUserAverageGrade(Long userId){
        return userGradeRepository.findUserGrade(userId);
    }

}
