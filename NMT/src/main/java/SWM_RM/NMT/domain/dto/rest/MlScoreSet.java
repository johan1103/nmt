package SWM_RM.NMT.domain.dto.rest;

import SWM_RM.NMT.domain.dto.ScoreSet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MlScoreSet {
    private Integer chongjumScore=0;
    private Integer dockhaeScore=0;
    private Integer nonliScore=0;
    private Integer pyohyunScore=0;

    public static ScoreSet scoreSetConverter(MlScoreSet mlScoreSet){
        ScoreSet scoreSet = new ScoreSet();
        scoreSet.setGrade1((double) mlScoreSet.getChongjumScore()/3);
        scoreSet.setGrade2((double) mlScoreSet.getDockhaeScore());
        scoreSet.setGrade3((double) mlScoreSet.getNonliScore());
        scoreSet.setGrade4((double) mlScoreSet.getPyohyunScore());
        scoreSet.setGrade5(1D);
        Double everageScore=scoreSet.getGrade1()+scoreSet.getGrade2()+ scoreSet.getGrade3()
                +scoreSet.getGrade4()+scoreSet.getGrade5();
        everageScore/=5;
        scoreSet.setTotalEverage(everageScore);
        return scoreSet;
    }
}
