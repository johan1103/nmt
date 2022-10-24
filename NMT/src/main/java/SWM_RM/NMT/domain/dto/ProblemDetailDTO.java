package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.domain.Problem;
import lombok.Getter;

import javax.persistence.*;

/**
 * 문제 풀이페이지에서 문제의 정보들을 controller에서 템플릿 제공용도로 가공하는 DTO
 */
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
