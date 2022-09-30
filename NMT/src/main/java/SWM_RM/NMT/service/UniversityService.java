package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    /**
     * 문제 목록 페이지에서 유저가 선택하기 위해 보여주는 선택지를 return하는 메서드
     * @return
     */
    public List<University> universityListService(){
        return universityRepository.findAllUniversity();
    }
}
