package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.DbTest;
import SWM_RM.NMT.domain.dto.DbTestDTO;
import SWM_RM.NMT.repository.DbTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println("hello world!");
        //return "ok";
        return dbTestRepository.createDbTest(dbTestDTO);
    }

}
