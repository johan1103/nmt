package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.dto.ProblemListDTO;
import SWM_RM.NMT.service.UserGradeHistoryService;
import SWM_RM.NMT.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserGradeHistoryService userGradeHistoryService;
    private final UserService userService;
    @GetMapping("/my-profile")
    public String myprofileController(Model model,@RequestParam(value = "gradeNum") @Nullable Long gradeNum){
        Long userId = 21L;
        HashMap<Integer,Integer> userStrick=userGradeHistoryService.userGradeHistoryStrickService(userId);
        HashMap<Integer,Double> strick=new HashMap<>();
        List<ProblemListDTO> problems=userGradeHistoryService.userSolvedProblemListService(userId);
        if(gradeNum==null)
            strick=userGradeHistoryService.userTotalGradeHistoryServcie(userId);
        else
            strick=userGradeHistoryService.userSpecificGradeHistoryService(userId,gradeNum);
        System.out.println(gradeNum);
        model.addAttribute("strick",userStrick);
        model.addAttribute("problems",problems);
        model.addAttribute("user",userService.findUserService(userId));
        model.addAttribute("totalStrick",strick);
        return "profile/profile";
    }

}
