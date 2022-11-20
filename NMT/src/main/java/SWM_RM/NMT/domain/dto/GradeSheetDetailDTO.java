package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGradeSheet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 특정 성적표를 상세 조회할때 return 하는 DTO
 */
@Getter
@Setter
public class GradeSheetDetailDTO {
    private String userNickName;
    private Long userId;
    private String text;

    private Double grade1;
    private Double grade2;
    private Double grade3;
    private Double grade4;
    private Double grade5;
    private String grade;

    private String bestText;
    private String probExp;
    private String probTitle;
    private String probText;
    private Long problemId;

    public static GradeSheetDetailDTO gradeSheetDetailDtoConverter(UserGradeSheet userGradeSheet,
                                                                   Problem problem,
                                                                   User user){
        GradeSheetDetailDTO gradeSheetDetailDTO = new GradeSheetDetailDTO();
        gradeSheetDetailDTO.setGrade1(Math.round(userGradeSheet.getGrade1() * 100) / 100.0);
        gradeSheetDetailDTO.setGrade2(Math.round(userGradeSheet.getGrade2() * 100) / 100.0);
        gradeSheetDetailDTO.setGrade3(Math.round(userGradeSheet.getGrade3() * 100) / 100.0);
        gradeSheetDetailDTO.setGrade4(Math.round(userGradeSheet.getGrade4() * 100) / 100.0);
        gradeSheetDetailDTO.setGrade5(userGradeSheet.getGrade5());
        gradeSheetDetailDTO.setGrade(userGradeSheet.getGrade());
        gradeSheetDetailDTO.setText(userGradeSheet.getReportText());

        gradeSheetDetailDTO.setBestText(problem.getBestText());
        gradeSheetDetailDTO.setProbExp(problem.getProbExp());
        gradeSheetDetailDTO.setProbTitle(problem.getProbTitle());
        gradeSheetDetailDTO.setProbText(problem.getProbText());
        gradeSheetDetailDTO.setProblemId(problem.getId());

        gradeSheetDetailDTO.setUserNickName(user.getNickName());
        gradeSheetDetailDTO.setUserId(user.getId());
        return gradeSheetDetailDTO;
    }
}
