package SWM_RM.NMT.controller;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.dto.GradeSheetListDTO;
import SWM_RM.NMT.domain.dto.ProblemDetailDTO;
import SWM_RM.NMT.domain.dto.ProblemListDTO;
import SWM_RM.NMT.domain.dto.UserDTO;
import SWM_RM.NMT.service.ProblemService;
import SWM_RM.NMT.service.UserGradeSheetService;
import SWM_RM.NMT.service.UserService;
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
    private final UserGradeSheetService userGradeSheetService;
    private final UserService userService;

    @GetMapping("")
    public String problemController(Model model
            , @RequestParam(value = "problemId") Long problemId){
        ProblemDetailDTO problemDetailDTO=problemService.problemPageService(problemId);
        model.addAttribute("problem",problemDetailDTO);
        System.out.println("----problem title: "+problemDetailDTO.getProbTitle());
        return "/problem/main";
    }
    @GetMapping("/user-solved-list")
    public String userSolvedListController(Model model, @RequestParam(value = "problemId") Long problemId){
        /**
         * user id는 security에서 가져와야 함
         */
        Long userId = 21L;
        System.out.println("----------user-solved-list controller--------");
        System.out.println(problemId+" "+userId);
        List<GradeSheetListDTO> userGradeSheetList=userGradeSheetService.userGradeSheetListService(userId,problemId);
        ProblemDetailDTO problemDetail=problemService.problemPageService(problemId);
        model.addAttribute("gradeList",userGradeSheetList);
        model.addAttribute("problem",problemDetail);
        System.out.println("user "+userGradeSheetList.get(0).getUserNickname()+", "
                +userGradeSheetList.get(0).getTotalGrade());
        return "/problem/mainUserSolved";
    }

    @GetMapping("/solved-list")
    public String solvedListController(Model model, @RequestParam(value = "problemId") Long problemId){
        System.out.println("----------solved-list controller--------");
        System.out.println(problemId);
        List<GradeSheetListDTO> userGradeSheetList=userGradeSheetService.gradeSheetListService(problemId);
        ProblemDetailDTO problemDetail=problemService.problemPageService(problemId);
        model.addAttribute("gradeList",userGradeSheetList);
        model.addAttribute("problem",problemDetail);
        System.out.println("user "+userGradeSheetList.get(0).getUserNickname()+", "
                +userGradeSheetList.get(0).getTotalGrade());
        return "/problem/mainSolved";
    }

    @GetMapping("/list")
    public String problemListController(Model model){
        /**
         * user id는 security에서 가져와야 함
         */
        Long userId = 21L;
        System.out.println("-----------problem-list controller");
        List<ProblemListDTO> problemDtoList=problemService.problemListService();
        UserDTO userDTO = userService.findUserService(userId);
        model.addAttribute("problems",problemDtoList);
        model.addAttribute("user",userDTO);
        return "/problem/problemList";
    }
}