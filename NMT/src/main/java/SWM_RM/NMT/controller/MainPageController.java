package SWM_RM.NMT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("")
    public String mainPageController(){
        return "main";
    }
}
