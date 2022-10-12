package SWM_RM.NMT.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 문제 제출 페이지에서 제출할 때, controller에서 받아오는 DTO
 */
@Getter
@Setter
public class GradeSheetDTO {
    private String text;
}
