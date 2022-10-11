package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.domain.Problem;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class ProblemDetailDTO {
    private Long id;
    private String probText;
    private String probTitle;
    private Double competetionRate;
    private Long recommendedSubmissionSize;
    private Long solvedNum;
    public static ProblemDetailDTO problemDtoConverter(Problem problem){
        ProblemDetailDTO problemDetailDTO = new ProblemDetailDTO();
        problemDetailDTO.id=problem.getId();
        problemDetailDTO.probText=problem.getProbText();
        problemDetailDTO.probTitle=problem.getProbTitle();
        problemDetailDTO.competetionRate=problem.getCompetetionRate();
        problemDetailDTO.recommendedSubmissionSize=problem.getRecommendedSubmissionSize();
        problemDetailDTO.solvedNum= problem.getSolvedNum();
        return problemDetailDTO;
    }
}
