package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.domain.Problem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemListDTO {
    private Long solvedNum;
    private String probTitle;
    private Long createYear;
    private String probTypeName;
    private Long problemId;

    public static ProblemListDTO problemListDtoConverter(Problem problem){
        ProblemListDTO problemListDTO = new ProblemListDTO();
        problemListDTO.setSolvedNum(problem.getSolvedNum());
        problemListDTO.setProbTitle(problem.getProbTitle());
        problemListDTO.setCreateYear(problem.getCreateYear());
        problemListDTO.setProbTypeName(problem.getProbType().getTypeName());
        problemListDTO.setProblemId(problem.getId());
        return problemListDTO;
    }
}
