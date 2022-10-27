package SWM_RM.NMT.controller;

import SWM_RM.NMT.domain.dto.ProblemListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    @GetMapping("/my-profile")
    public String myprofileController(Model model){
        List<ProblemListDTO> problemList;
        return "profile/profile";
    }

}
