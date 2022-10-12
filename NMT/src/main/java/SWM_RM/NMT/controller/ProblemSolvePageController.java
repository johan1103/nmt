package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.dto.GradeSheetDTO;
import SWM_RM.NMT.repository.UserRepository;
import SWM_RM.NMT.service.UserGradeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submitGradeSheet")
@RequiredArgsConstructor
public class ProblemSolvePageController {
    private final UserGradeSheetService userGradeSheetService;

    /**
     * 여기 밑의 bean 객체들은 임시 사용 용도.
     */
    private final UserRepository userRepository;
    @PostMapping("")
    public String submitUserGradeSheet(@RequestParam(value = "problemId") Long problemId
            ,@RequestBody GradeSheetDTO gradeSheetDTO
            , Model model){
        //원래는 스프링 시큐리티로 userId 가져와야 함. 지금은 일단 아무나 가져옴.
        Long userId=userRepository.findUserList().get(0).getId();

        userGradeSheetService.submitUserGradeSheetService(gradeSheetDTO.getText(),
                userId,problemId);
        System.out.println("user "+userId);

        return "redirect:/problem?problemId="+problemId;
    }
}
