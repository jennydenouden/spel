package nl.vyjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpelerController {
    
	@Autowired
	private SpelerRepository repo;
	
    @RequestMapping("/speler")
    public String showSpeler(Model model){
       // model.addAttribute("inventaris", );
        return "showSpeler";
    }
}
