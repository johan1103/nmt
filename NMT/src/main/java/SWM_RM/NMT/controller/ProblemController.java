package SWM_RM.NMT.controller;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.dto.*;
import SWM_RM.NMT.security.JwtTokenProvider;
import SWM_RM.NMT.service.ProblemService;
import SWM_RM.NMT.service.UniversityService;
import SWM_RM.NMT.service.UserGradeSheetService;
import SWM_RM.NMT.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

@Controller
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;
    private final UserGradeSheetService userGradeSheetService;
    private final UserService userService;
    private final UniversityService universityService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("")
    public String problemController(Model model
            , @RequestParam(value = "problemId") Long problemId){
        ProblemDetailDTO problemDetailDTO=problemService.problemPageService(problemId);
        model.addAttribute("problem",problemDetailDTO);
        return "problems/main";
    }
    @GetMapping("/user-solved-list")
    public String userSolvedListController(Model model,@CookieValue(value = "nmt-token")Cookie cookie,
                                           @RequestParam(value = "problemId") Long problemId){
        Long userId = jwtTokenProvider.getUserIdFromToken(cookie.getValue());
        List<GradeSheetListDTO> userGradeSheetList=userGradeSheetService.userGradeSheetListService(userId,problemId);
        ProblemDetailDTO problemDetail=problemService.problemPageService(problemId);
        model.addAttribute("gradeList",userGradeSheetList);
        model.addAttribute("problem",problemDetail);
        return "problems/mainUserSolved";
    }

    @GetMapping("/solved-list")
    public String solvedListController(Model model, @RequestParam(value = "problemId") Long problemId){
        System.out.println("----------solved-list controller--------");
        System.out.println(problemId);
        List<GradeSheetListDTO> userGradeSheetList=userGradeSheetService.gradeSheetListService(problemId);
        ProblemDetailDTO problemDetail=problemService.problemPageService(problemId);
        model.addAttribute("gradeList",userGradeSheetList);
        model.addAttribute("problem",problemDetail);
        return "problems/mainSolved";
    }

    @GetMapping("/list")
    public String problemListController(Model model, @CookieValue(value = "nmt-token")Cookie cookie,
                                        @RequestParam(value = "univName") @Nullable String univName){
        Long userId = jwtTokenProvider.getUserIdFromToken(cookie.getValue());
        List<ProblemListDTO> problemDtoList;
        List<UniversityDTO> universityDtoList;
        if(univName==null)
            problemDtoList=problemService.problemListService();
        else
            problemDtoList=problemService.problemListFilterService(null,univName,null);
        UserDTO userDTO = userService.findUserService(userId);
        universityDtoList = universityService.universityListService();
        model.addAttribute("problems",problemDtoList);
        model.addAttribute("user",userDTO);
        model.addAttribute("universities",universityDtoList);
        return "problems/problemList";
    }

}
