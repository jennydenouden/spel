package nl.vyjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.vyjy.Bootje;
import nl.vyjy.Spel;
import nl.vyjy.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpelController {

    @Autowired
    private BootjesRepository bootjeRepo;

    @Autowired
    private SpelerRepository repo;

    @Autowired
    private SpelRepository spelRepo;

    @RequestMapping("/spel")
    public String spel(Model model, HttpServletRequest request) {
        //Default id voor het geval we nog random willen kijken
        long spelId = 1l;

        //Vraag het id van het spel op uit de session
        HttpSession session = request.getSession();
        Object idObj = session.getAttribute("spelId");
        if (idObj != null) {
            spelId = (long) idObj;
        }

        //Vraag het juiste spel op
        Spel s = spelRepo.findOne(spelId);
        model.addAttribute("spelers", s.getSpelers());
        model.addAttribute("bootjeswinkel", spelRepo.findOne(spelId).getBootjesWinkel());
        return "spel";
    }

    @RequestMapping(value = "/visjebij{id}", method = RequestMethod.GET)
    public String krijgVisjes(@PathVariable long id, HttpServletResponse response) {
        Speler s = repo.findOne(id);
        s.setVisjes(s.getVisjes() + 1);
        repo.save(s);
        return "redirect:/spel";
    }

    @RequestMapping(value = "/banaanbij{id}", method = RequestMethod.GET)
    public String krijgBanaan(@PathVariable long id, HttpServletResponse response) {
        Speler s = repo.findOne(id);
        s.setBananen(s.getBananen() + 1);
        repo.save(s);
        return "redirect:/spel";
    }

    @RequestMapping(value = "/schelpbij{id}", method = RequestMethod.GET)
    public String krijgSchelpen(@PathVariable long id, HttpServletResponse response) {
        Speler s = repo.findOne(id);
        s.setSchelpen(s.getSchelpen() + 1);
        repo.save(s);
        return "redirect:/spel";
    }

    @RequestMapping(value = "/visjeaf{id}", method = RequestMethod.GET)
    public String geefVisjes(@PathVariable long id, HttpServletResponse response) {
        Speler s = repo.findOne(id);
        if (s.getVisjes() > 0) {
            s.setVisjes(s.getVisjes() - 1);
            repo.save(s);
        }
        return "redirect:/spel";
    }

    @RequestMapping(value = "/banaanaf{id}", method = RequestMethod.GET)
    public String geefBanaan(@PathVariable long id, HttpServletResponse response) {
        Speler s = repo.findOne(id);
        if (s.getBananen() > 0) {
            s.setBananen(s.getBananen() - 1);
            repo.save(s);
        }
        return "redirect:/spel";
    }

    @RequestMapping(value = "/schelpaf{id}", method = RequestMethod.GET)
    public String geefSchelpen(@PathVariable long id, HttpServletResponse response) {
        Speler s = repo.findOne(id);
        if (s.getSchelpen() > 0) {
            s.setSchelpen(s.getSchelpen() - 1);
            repo.save(s);
        }
        return "redirect:/spel";
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

    @RequestMapping(value = "/koop/{id}", method = RequestMethod.GET)
    public String koopBootje(@PathVariable long id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Bootje b = bootjeRepo.findOne(id);
        if (b == null || b.isVerkocht()) {
            response.sendError(404, "Dat bootje bestaat niet");
            return null;
        } else {
            //Default id voor het geval we nog random willen kijken
            long spelId = 1l;

            //Vraag het id van het spel op uit de session
            HttpSession session = request.getSession();
            Object idObj = session.getAttribute("spelId");
            if (idObj != null) {
                spelId = (long) idObj;
            }
            Spel s = spelRepo.findOne(spelId);
            Speler speler = s.getHuidigeSpeler();
            Object idObjSpeler = session.getAttribute("spelerId");
            if (idObjSpeler != null) {
                long spelerId = (long) idObjSpeler;
                if (spelerId == speler.getId()) {

                    if (speler.koopBootje(b)) {
                        b.setVerkocht(true);
                        bootjeRepo.save(b);
                        //Werk de bootjeswinkel bij
                        s.getBootjesWinkel().koopBootje(b);
                        s.getBootjesWinkel().addBootje(getEersteOnverkochteBootje());
                        spelRepo.save(s);
                    } else {
                        response.sendError(404, "Jij kan dit bootje niet betalen");
                        return null;
                    }
                } else {
                    response.sendError(404, "Jij bent niet aan de beurt.");
                    System.err.println("id van deze speler: " + spelerId + "\nid van de huidige speler: " + speler.getId());
                    return null;
                }
            }

            return "redirect:/bootjes";
        }
    }

    private Bootje getEersteOnverkochteBootje() {
        Spel s = spelRepo.findOne(1l);
        List<Bootje> bootjes = s.getAlleBootjes();
        Bootje result = null;
        for (Bootje b : bootjes) {
            if (!b.isVerkocht() && !s.getBootjesWinkel().getBootjesTeKoop().contains(b)) {
                result = b;
                break;
            }
        }

        return result;
    }
}
