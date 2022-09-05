package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.dto.ScoreSet;
import org.springframework.stereotype.Service;

@Service
public class ScoreSetService {
    public ScoreSet gradingScore(){
        ScoreSet scoreSet = new ScoreSet();
        scoreSet.setGrade1(13D);
        scoreSet.setGrade2(13D);
        scoreSet.setGrade3(13D);
        scoreSet.setGrade4(13D);
        scoreSet.setGrade5(13D);
        scoreSet.setTotalEverage(13D);
        scoreSet.setGrade("A");
        return scoreSet;
    }
}
