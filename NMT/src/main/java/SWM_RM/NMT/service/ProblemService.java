package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.dto.AnswerSheetDTO;
import SWM_RM.NMT.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository pr;

    public List<Problem> findList(String universityName, String probTypeName, Long year){
        return pr.findProblems(universityName,probTypeName,year);
    }

    public Problem findById(Long problemId){
        return pr.findProblemById(problemId);
    }

    public AnswerSheetDTO getBestTextRepository(Long problemId){
        Problem problem = pr.findProblemById(problemId);
        AnswerSheetDTO answerSheetDTO = new AnswerSheetDTO();
        answerSheetDTO.setBestText(problem.getBestText());
        answerSheetDTO.setProbExp(problem.getProbExp());
        return answerSheetDTO;
    }
    public List<Problem> findUserProblemListService(Long userId){
        List<Problem> problems = new ArrayList<Problem>();
        HashSet<Problem> problemHashSet = pr.findUserProblemList(userId);
        Iterator<Problem> iter = problemHashSet.iterator();
        while(iter.hasNext()){
            problems.add(iter.next());
        }
        return problems;
    }
}
