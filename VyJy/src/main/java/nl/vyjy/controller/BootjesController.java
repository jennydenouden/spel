package nl.vyjy.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.vyjy.Bootje;
import nl.vyjy.BootjesWinkel;
import nl.vyjy.SetTegels;
import nl.vyjy.Spel;
import nl.vyjy.Speler;
import nl.vyjy.Tegel;

@Controller
public class BootjesController {

    @Autowired
    private SpelRepository spelRepo;

    @RequestMapping("/*")
    public String chooseGame(Model model, HttpServletRequest request) {
        model.addAttribute("spellen", spelRepo.findAll());

        //Als er al een spel actief is: zet dat in het model
        HttpSession session = request.getSession();
        request.setAttribute("spelId", session.getAttribute("spelId"));
        return "kiesgame";
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String createNewGame() {
        Spel s = new Spel();
        spelRepo.save(s);

        //voeg bootjes toe
        ArrayList<Bootje> bootjes = getAllBootjes();
        s.setAlleBootjes(bootjes);
        s = spelRepo.save(s);

        //voeg kaartjes toe
        ArrayList<Tegel> tegels = SetTegels.getAlleTegels();
        s.setAlleTegels(tegels);
        s.setHuidigeTegel(tegels.get(0));
        spelRepo.save(s);

        //voeg bootjeswinkel toe
        BootjesWinkel b = new BootjesWinkel();
        b.setBootjesTeKoop(s.getAlleBootjes().subList(0, 4));
        s.setBootjesWinkel(b);
        spelRepo.save(s);

        return "redirect:/start";
    }

    @RequestMapping("/spel/{id}")
    public String joinGame(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("spelId", id);
        //return "redirect:/bord";
        return "redirect:/spel";
    }

    /*
	 * Dit is als je via de form een nieuwe speler maakt (wat je dus eigenlijk niet wil)
	 * haha nu weer wel :(
     */
    @RequestMapping(value = "/spel/{id}/nieuwespeler", method = RequestMethod.POST)
    public String createdPlayer(String naam, @PathVariable long id) {
        Spel s = spelRepo.findOne(id);
        List<Speler> spelers = s.getSpelers();

        Speler nieuweSpeler = new Speler(naam);
        spelers.add(nieuweSpeler);
        if (spelers.size() == 1) {
            s.setHuidigeSpeler(nieuweSpeler);
        }
        spelRepo.save(s);

        return "redirect:/start";
    }

    /*
	 * Speler toevoegen als je op het plusje klikt
     */
    @RequestMapping(value = "/spel/{id}/maakspeler", method = RequestMethod.GET)
    public String createPlayer(@PathVariable long id, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        model.addAttribute("gameid", id);
        Spel s = spelRepo.findOne(id);
        spelRepo.save(s);

        return "maakspeler";
    }

    /*
	 * Laat de bootjeswinkel zien
     */
    /*@RequestMapping("/bootjes") // spel?
    public String initBootjes() {
        return "showBootjes";
    }*/

    /*
	 * Maakt alle bootjes uit Feia's excel file aan, en
	 * geeft die terug
     */
    private ArrayList<Bootje> getAllBootjes() {
        ArrayList<Bootje> bootjes = new ArrayList<>();

        Bootje b;
        b = new Bootje(3, 2, 1, 0);
        bootjes.add(b);

        b = new Bootje(6, 2, 1, 2);
        bootjes.add(b);

        b = new Bootje(4, 0, 2, 2);
        bootjes.add(b);

        b = new Bootje(2, 1, 0, 1);
        bootjes.add(b);

        b = new Bootje(2, 0, 1, 1);
        bootjes.add(b);

        b = new Bootje(3, 2, 0, 1);
        bootjes.add(b);

        b = new Bootje(5, 1, 1, 3);
        bootjes.add(b);

        Collections.shuffle(bootjes);

        return bootjes;
    }
}
