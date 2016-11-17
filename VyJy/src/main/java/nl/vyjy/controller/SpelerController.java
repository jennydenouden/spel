package nl.vyjy.controller;


import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import nl.vyjy.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
    @RequestMapping(value="/visjebij{id}", method=RequestMethod.GET)
    public String krijgVisjes(@PathVariable long id, HttpServletResponse response){
        Speler s = repo.findOne(id);
        s.setVisjes(s.getVisjes() + 1);
        repo.save(s);
        return "redirect:/speler";
    }
    
        @RequestMapping(value="/banaanbij{id}", method=RequestMethod.GET)
    public String krijgBanaan(@PathVariable long id, HttpServletResponse response){
        Speler s = repo.findOne(id);
        s.setBananen(s.getBananen() + 1);
        repo.save(s);
        return "redirect:/speler";
    }
    
        @RequestMapping(value="/schelpbij{id}", method=RequestMethod.GET)
    public String krijgSchelpen(@PathVariable long id, HttpServletResponse response){
        Speler s = repo.findOne(id);
        s.setSchelpen(s.getSchelpen() + 1);
        repo.save(s);
        return "redirect:/speler";
    }
    
        @RequestMapping(value="/visjeaf{id}", method=RequestMethod.GET)
    public String geefVisjes(@PathVariable long id, HttpServletResponse response){
        Speler s = repo.findOne(id);
        if(s.getVisjes() > 0 ){
            s.setVisjes(s.getVisjes() - 1);
            repo.save(s);
        }
        return "redirect:/speler";
    }
    
        @RequestMapping(value="/banaanaf{id}", method=RequestMethod.GET)
    public String geefBanaan(@PathVariable long id, HttpServletResponse response){
        Speler s = repo.findOne(id);
        if(s.getBananen() > 0){
            s.setBananen(s.getBananen() - 1);
            repo.save(s);
        }
        return "redirect:/speler";
    }
    
        @RequestMapping(value="/schelpaf{id}", method=RequestMethod.GET)
    public String geefSchelpen(@PathVariable long id, HttpServletResponse response){
        Speler s = repo.findOne(id);
        if(s.getSchelpen() > 0){
            s.setSchelpen(s.getSchelpen() - 1);
            repo.save(s);
        }
        return "redirect:/speler";
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
