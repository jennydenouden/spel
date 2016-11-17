package nl.vyjy.controller;


import java.util.ArrayList;
import nl.vyjy.Speler;
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
        
        if(repo.count() == 0){
            ArrayList<Speler> spelers = getAllSpelers();
            for(Speler s : spelers){
                    repo.save(s);
            }
        }
        
        model.addAttribute("spelers", repo.findAll());
        return "showSpeler";
    }

    private ArrayList<Speler> getAllSpelers() {
        ArrayList<Speler> spelers = new ArrayList<>();
        
        Speler s;
        s = new Speler("Jenny");
        spelers.add(s);
        s = new Speler("Feia");
        spelers.add(s);
        
        return spelers;
    }
}
