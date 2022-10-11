package SWM_RM.NMT.tutorial.thymeleaf;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class RestController {
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        tempUser user=new tempUser();
        user.setName("kim");
        model.addAttribute("data", user);
        return "basic/text-basic";
    }
}
