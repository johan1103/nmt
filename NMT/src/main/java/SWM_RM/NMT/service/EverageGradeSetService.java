package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.EverageGradeSet;
import SWM_RM.NMT.repository.EverageGradeSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EverageGradeSetService {
    private final EverageGradeSetRepository everageGradeSetRepository;

    public EverageGradeSet getEverageGradeSetService(Long gradeSetId){
        return everageGradeSetRepository.getEverageGradeSetRepository(gradeSetId);
    }
}
