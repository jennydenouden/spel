package nl.vyjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BordController {
    
    @RequestMapping("/bord")
    public String bord(){
        return "showBord";
    }
}
