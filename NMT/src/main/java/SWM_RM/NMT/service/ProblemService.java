package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.dto.ProblemDetailDTO;
import SWM_RM.NMT.domain.dto.ProblemListDTO;
import SWM_RM.NMT.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    /**
     * 모든 문제들을 조회하고자 할 때, problemRepository로 문제 리스트를 요청하는 메서드
     * @return List<Problem>
     */
    public List<ProblemListDTO> problemListService(){
        List<Problem> problems = problemRepository.findProblemList();
        List<ProblemListDTO> problemDtolist=new ArrayList<>();
        for(Problem problem : problems){
            problemDtolist.add(ProblemListDTO.problemListDtoConverter(problem));
        }
        return problemDtolist;
    }

    /**
     * 필터 요청이 들어왔을 때, 필터대로 문제를 걸러서 return하는 메서드
     * @param probTypeName
     * @param universityName
     * @param createYear
     * @return
     */
    public List<ProblemListDTO> problemListFilterService(String probTypeName, String universityName, Long createYear){
        List<Problem> problems=new ArrayList<>();
        if(probTypeName!=null && universityName!=null && createYear!=null)
             problems=problemRepository.findProblemListFilter(universityName,
                    probTypeName,createYear);
        if(probTypeName==null && universityName!=null && createYear==null)
             problems=problemRepository.findProblemListOneFilter1(universityName);
        if(probTypeName!=null && universityName==null && createYear==null)
             problems=problemRepository.findProblemListOneFilter2(probTypeName);
        if(probTypeName==null && universityName==null && createYear!=null)
             problems=problemRepository.findProblemListOneFilter3(createYear);
        List<ProblemListDTO> problemDtolist=new ArrayList<>();
        for(Problem problem : problems){
            problemDtolist.add(ProblemListDTO.problemListDtoConverter(problem));
        }
        return problemDtolist;
    }
    /**
     * 문제 정보 조회 서비스, 문제의 상세 정보를 전부 return 해야함
     */
    public ProblemDetailDTO problemPageService(Long problemId){
        Problem problem = problemRepository.findProblemById(problemId);
        ProblemDetailDTO problemDetailDTO = ProblemDetailDTO.problemDtoConverter(problem);
        return problemDetailDTO;
    }
}
