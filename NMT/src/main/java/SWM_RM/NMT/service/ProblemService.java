package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository pr;

    public List<Problem> findList(String universityName, String probTypeName, Long year){
        return pr.findProblems(universityName,probTypeName,year);
    }
}
