package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.DbTest;
import SWM_RM.NMT.domain.dto.DbTestDTO;
import SWM_RM.NMT.domain.dto.PrototypeSheetDTO;
import SWM_RM.NMT.repository.DbTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterChain;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class RestControllerTest {
    private final DbTestRepository dbTestRepository;
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

}
