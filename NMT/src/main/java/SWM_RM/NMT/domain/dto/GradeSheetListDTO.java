package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.domain.UserGradeSheet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

/**
 * 특정 문제의 성적표 List들을 조회하는 Controller에서 문제 list정보를 제공하는 DTO
 */
@Setter
@Getter
public class GradeSheetListDTO {
    private Long id;
    private String userNickname;
    private String totalGrade;
    private Double grade1;
    private Double grade2;
    private Double grade3;
    private Double grade4;
    private Double grade5;
    private Integer textSize;
    private String createTime;

    public static GradeSheetListDTO gradeSheetDtoConverter(UserGradeSheet userGradeSheet){
        GradeSheetListDTO gradeSheetDTO = new GradeSheetListDTO();
        gradeSheetDTO.setId(userGradeSheet.getId());
        gradeSheetDTO.setGrade1(userGradeSheet.getGrade1());
        gradeSheetDTO.setGrade2(userGradeSheet.getGrade2());
        gradeSheetDTO.setGrade3(userGradeSheet.getGrade3());
        gradeSheetDTO.setGrade4(userGradeSheet.getGrade4());
        gradeSheetDTO.setTotalGrade(userGradeSheet.getGrade());
        gradeSheetDTO.setTextSize(userGradeSheet.getTextSize());
        gradeSheetDTO.setUserNickname(userGradeSheet.getUser().getNickName());
        LocalDateTime createTime=userGradeSheet.getCreateTime();
        gradeSheetDTO.setCreateTime(localDateTimeToStringConverter(createTime));
        return gradeSheetDTO;
    }
    public static String localDateTimeToStringConverter(LocalDateTime localDateTime){
        String createTime=new String();
        createTime=localDateTime.getYear()+"년 "+localDateTime.getMonthValue()+"월 "+localDateTime.getDayOfMonth()+"일";
        return createTime;
    }
}
