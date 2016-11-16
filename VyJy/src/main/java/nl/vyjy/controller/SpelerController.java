package nl.vyjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpelerController {
    
    @RequestMapping("/speler")
    public String showSpeler(Model model){
        model.addAttribute("inventaris", );
        return "showSpeler";
    }
}
