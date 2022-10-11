package SWM_RM.NMT.controller;

import SWM_RM.NMT.data.CreateProblemDatas2;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.dto.ProblemDetailDTO;
import SWM_RM.NMT.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/")
    public String problemController(Model model
            , @RequestParam(value = "problemId",required = false) Long problemId){
        //임시 데이터 아무거나 가져오기용
        List<Problem> problemList = problemService.problemListService();

        Problem problem = problemService.problemPageService(problemList.get(0).getId());
        //Problem problem = problemService.problemPageService(problemList.get(0).getId());
        ProblemDetailDTO problemDetailDTO=ProblemDetailDTO.problemDtoConverter(problem);
        model.addAttribute("problem",problemDetailDTO);
        System.out.println("----problem title: "+problemDetailDTO.getProbTitle());
        return "/problem/main";
    }
}
