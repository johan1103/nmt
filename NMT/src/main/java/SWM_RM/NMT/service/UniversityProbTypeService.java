package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.repository.UniversityProbTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityProbTypeService {
    private final UniversityProbTypeRepository universityProbTypeRepository;


}
