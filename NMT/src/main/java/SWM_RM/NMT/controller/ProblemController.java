package SWM_RM.NMT.controller;

import SWM_RM.NMT.data.CreateProblemDatas2;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.dto.GradeSheetDTO;
import SWM_RM.NMT.domain.dto.ProblemDetailDTO;
import SWM_RM.NMT.repository.UserRepository;
import SWM_RM.NMT.service.ProblemService;
import SWM_RM.NMT.service.UserGradeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("")
    public String problemController(Model model
            , @RequestParam(value = "problemId") Long problemId){

        Problem problem = problemService.problemPageService(problemId);
        ProblemDetailDTO problemDetailDTO=ProblemDetailDTO.problemDtoConverter(problem);
        model.addAttribute("problem",problemDetailDTO);
        System.out.println("----problem title: "+problemDetailDTO.getProbTitle());
        return "/problem/main";
    }
    @GetMapping("/list")
    public String problemListController(Model model, @RequestParam(value = "problemId") Long problemId){
        System.out.println("----------redirected--------");
        System.out.println(problemId);
        return "/problem/mainUserSolved";
    }
}
