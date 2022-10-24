package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.dto.GradeSheetDetailDTO;
import SWM_RM.NMT.service.UserGradeSheetService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/grade-sheet")
@RequiredArgsConstructor
public class GradeSheetController {

    private final UserGradeSheetService userGradeSheetService;

    @GetMapping("")
    public String gradeSheetController(Model model, @RequestParam(value = "gradeSheetId") Long gradeSheetId){
        System.out.println("--------gradeSheet Controller");
        System.out.println(gradeSheetId);
        GradeSheetDetailDTO gradeSheetDetailDTO = userGradeSheetService.userGradeSheetService(gradeSheetId);
        model.addAttribute("gradeSheet",gradeSheetDetailDTO);
        return "gradeSheet/gradeSheet";
    }
}
