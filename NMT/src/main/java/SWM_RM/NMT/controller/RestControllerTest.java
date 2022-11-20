package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.DbTest;
import SWM_RM.NMT.domain.dto.DbTestDTO;
import SWM_RM.NMT.domain.dto.PrototypeSheetDTO;
import SWM_RM.NMT.domain.dto.rest.MlScoreSet;
import SWM_RM.NMT.domain.dto.rest.RequestText;
import SWM_RM.NMT.repository.DbTestRepository;
import SWM_RM.NMT.rest.RestSend;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterChain;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class RestControllerTest {
    private final DbTestRepository dbTestRepository;
    private final RestSend restSend;
    @GetMapping("/test/getDb")
    public List<DbTest> testController(){
        return dbTestRepository.getDbList();
    }
    @GetMapping("/test/postDb")
    public String testController(@RequestBody DbTestDTO dbTestDTO){
        System.out.println(dbTestDTO.getName());
        System.out.println(dbTestDTO.getAge());
        //return "ok";
        return dbTestRepository.createDbTest(dbTestDTO);
    }
    @GetMapping("/test/configTest")
    public String configTestController(){
        return "ans";
    }
    @PostMapping("/submit")
    public PrototypeSheetDTO tmpProblemSheetController(String essayContent){
        PrototypeSheetDTO prototypeSheetDTO = new PrototypeSheetDTO();
        prototypeSheetDTO.setLogicScore(1);
        prototypeSheetDTO.setExperssionScore(2);
        prototypeSheetDTO.setTotalScore(5);
        prototypeSheetDTO.setVocabularyScore(3);
        System.out.println(essayContent);
        return prototypeSheetDTO;
    }

    @PostMapping("/api/test")
    public ResponseEntity<MlScoreSet> restApiTest(@RequestBody RequestText text){
        MlScoreSet mlScoreSet = new MlScoreSet();
        mlScoreSet.setChongjumScore(1);
        mlScoreSet.setDockhaeScore(2);
        mlScoreSet.setNonliScore(3);
        mlScoreSet.setPyohyunScore(4);
        ResponseEntity<MlScoreSet> response = ResponseEntity.ok().body(mlScoreSet);
        System.out.println("------------called api/test");
        return response;
    }

    @GetMapping("/api-start")
    @ResponseBody
    public MlScoreSet restApiTestStart(){
        System.out.println("---called api-start");
        return restSend.sendEngineByForm("피카피카츄",69).getBody();
    }

}
