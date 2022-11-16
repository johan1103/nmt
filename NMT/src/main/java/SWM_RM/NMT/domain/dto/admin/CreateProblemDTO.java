package SWM_RM.NMT.domain.dto.admin;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProblemDTO {
    private String univName;
    private String probTypeName;
    private Long createYear;
    private Long recommendedTextSize;
    private String probTitle;
    private String probTextPath;
    private String probExp;
    private String probBestTextPath;
    private Double competitionRate;
    private Long problemId;
}
