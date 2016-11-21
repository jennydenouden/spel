package nl.vyjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.vyjy.Bootje;
import nl.vyjy.BootjesWinkel;
import nl.vyjy.SetTegels;
import nl.vyjy.Spel;
import nl.vyjy.Speler;
import nl.vyjy.Tegel;

@Controller
public class BootjesController {

	@Autowired
	private BootjesRepository repo;
	
	@Autowired
	private SpelRepository spelRepo;
	
	@Autowired
	private SpelerRepository spelerRepo;
	
	@RequestMapping("/start")
	public String chooseGame(Model model){
		model.addAttribute("spellen", spelRepo.findAll());
		return "kiesgame";
	}
		
	@RequestMapping(value = "/init", method=RequestMethod.POST)
	public String createNewGame(){
		Spel s = new Spel();
		spelRepo.save(s);
		
		//voeg bootjes toe
		ArrayList<Bootje> bootjes = getAllBootjes();
		s.setAlleBootjes(bootjes);
		s = spelRepo.save(s);
		
		//voeg kaartjes toe
		ArrayList<Tegel> tegels = SetTegels.getAlleTegels();
		s.setAlleTegels(tegels);
		spelRepo.save(s);
		
		//voeg bootjeswinkel toe
		BootjesWinkel b = new BootjesWinkel();
		b.setBootjesTeKoop(s.getAlleBootjes().subList(0, 4));	
		s.setBootjesWinkel(b);
		s = spelRepo.save(s);
		
		return "redirect:/start";
	}
	
	@RequestMapping("/spel/{id}")
	public @ResponseBody String joinGame(@PathVariable long id){
		return "Join spel " + id + ", als dat bestaat";
	}
	
	@RequestMapping(value = "/spel/{id}/nieuwespeler", method = RequestMethod.POST)
	public String createdPlayer(String naam, @PathVariable long id){
		Spel s = spelRepo.findOne(id);
		List<Speler> spelers = s.getSpelers();
		
		Speler nieuweSpeler = new Speler(naam);
		spelers.add(nieuweSpeler);
		if(spelers.size() == 1){
			s.setHuidigeSpeler(nieuweSpeler);
		}
		spelRepo.save(s);
		
		return "redirect:/start";
	}
	
	@RequestMapping(value = "/spel/{id}/maakspeler", method = RequestMethod.GET)
	public String createPlayer(@PathVariable long id, Model model){
		model.addAttribute("gameid", id);
		return "maakspeler";
	}
	
	
	
	
	/*
	 * Voegt de bootjes toe aan de database, als ze daar niet
	 * al in zaten. Dit gebeurt wanneer men naar de URL /bootjes
	 * surft. Vervolgens worden de bootjes weergegeven dmv de
	 * showBootjes jsp file.
	 */
	@RequestMapping("/bootjes")
	public String initBootjes(Model model){		
		model.addAttribute("bootjeswinkel", spelRepo.findOne(1l).getBootjesWinkel());
		return "showBootjes";
	}
	
	
	@RequestMapping(value="/koop/{id}", method=RequestMethod.GET)
	public String koopBootje(@PathVariable long id, HttpServletResponse response) throws IOException{
		Bootje b = repo.findOne(id);
		if(b == null || b.isVerkocht()){
			response.sendError(404, "Ongeldig bootje");
			return null;
		}
		else{
			Spel s = spelRepo.findOne(1l);
			Speler speler = s.getHuidigeSpeler();
			if(speler.koopBootje(b)){
				b.setVerkocht(true);
				repo.save(b);
				//Werk de bootjeswinkel bij
				s.getBootjesWinkel().koopBootje(b);
				s.getBootjesWinkel().addBootje(getEersteOnverkochteBootje());
				spelRepo.save(s);
			}
			else{
				response.sendError(404, "De speler kan dit bootje niet betalen");
				return null;
			}
			return "redirect:/bootjes";
		}
	}
	
	
	/*
	 * Geeft het eerste onverkochte bootje in de lijst met alle bootjes
	 */
	private Bootje getEersteOnverkochteBootje(){
		Spel s = spelRepo.findOne(1l);
		List<Bootje> bootjes = s.getAlleBootjes();
		Bootje result = null;
		for(Bootje b : bootjes){
			if(!b.isVerkocht() && !s.getBootjesWinkel().getBootjesTeKoop().contains(b) ){
				result = b;
				break;
			}
		}
		
		return result;
	}
	
	/*
	 * Maakt alle bootjes uit Feia's excel file aan, en
	 * geeft die terug
	 */
	private ArrayList<Bootje> getAllBootjes(){
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
