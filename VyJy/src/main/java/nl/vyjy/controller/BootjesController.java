package nl.vyjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

//	@Autowired
//	private BootjesRepository bootjeRepo;
	
	@Autowired
	private SpelRepository spelRepo;
	
	@Autowired
	private SpelerRepository spelerRepo;
	
	@Autowired
	private TegelRepository tegelRepo;
	
	@RequestMapping("/start")
	public String chooseGame(Model model, HttpServletRequest request){
		model.addAttribute("spellen", spelRepo.findAll());
		
		//Als er al een spel actief is: zet dat in het model
		HttpSession session = request.getSession();
		request.setAttribute("spelId", session.getAttribute("spelId"));
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
		s.setHuidigeTegel(tegels.get(0));
		spelRepo.save(s);

		//voeg bootjeswinkel toe
		BootjesWinkel b = new BootjesWinkel();
		b.setBootjesTeKoop(s.getAlleBootjes().subList(0, 4));	
		s.setBootjesWinkel(b);
		s = spelRepo.save(s);
		
		return "redirect:/start";
	}
	
	@RequestMapping("/spel/{id}")
	public String joinGame(@PathVariable long id, HttpServletRequest request){
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
	
	/*
	 * Speler toevoegen als je op het plusje klikt
	 */
	@RequestMapping(value = "/spel/{id}/maakspeler", method = RequestMethod.GET)
	public String createPlayer(@PathVariable long id, HttpServletRequest request, Model model){
		
		HttpSession session = request.getSession();
		String naam = (String) session.getAttribute("naam");
		model.addAttribute("gameid", id);
		Spel s = spelRepo.findOne(id);
		spelRepo.save(s);

		return "maakspeler";
	}
	
	/*
	 * Laat de bootjeswinkel zien
	 */
	@RequestMapping("/bootjes") // spel?
	public String initBootjes(){	
		return "showBootjes";
	}
	
//	@RequestMapping("/bordJenny")
//	public String showBord(Model model, HttpServletRequest request){
//		//Default id voor het geval we nog random willen kijken
//		long spelId = 1l;
//		
//		//Vraag het id van het spel op uit de session
//		HttpSession session = request.getSession();
//		Object idObj = session.getAttribute("spelId");
//		if(idObj != null){
//			spelId = (long)idObj;
//		}
//		
//		
//		List<Tegel> alleTegels = spelRepo.findOne(spelId).getAlleTegels();
//		
//		int index = getIndexHuidigeTegel(request);
//		if(index == 13){
//			model.addAttribute("tegelsOpBord", alleTegels);
//			model.addAttribute("huidigeTegel", null);
//			model.addAttribute("tegelsOpStapel", new ArrayList<Tegel>());
//		}
//		else{
//			List<Tegel> tegelsOpBord = alleTegels.subList(0, index);
//			Tegel huidigeTegel = alleTegels.get(index);
//			List<Tegel> tegelsOpStapel = new ArrayList<>();
//			if(index < alleTegels.size()-1){
//				tegelsOpStapel= alleTegels.subList(index+1, alleTegels.size());
//			}
//			
//			model.addAttribute("tegelsOpBord", tegelsOpBord);
//			model.addAttribute("huidigeTegel", huidigeTegel);
//			model.addAttribute("tegelsOpStapel", tegelsOpStapel);
//			
//			model.addAttribute("spelId", spelId);
//			model.addAttribute("spelers", spelRepo.findOne(spelId).getSpelers());
//		}
//			
//		return "bord";
//	}
	
	/*
	 * Geeft het eerste onverkochte bootje in de lijst met alle bootjes
	 */
//	private Bootje getEersteOnverkochteBootje(){
//		Spel s = spelRepo.findOne(1l);
//		List<Bootje> bootjes = s.getAlleBootjes();
//		Bootje result = null;
//		for(Bootje b : bootjes){
//			if(!b.isVerkocht() && !s.getBootjesWinkel().getBootjesTeKoop().contains(b) ){
//				result = b;
//				break;
//			}
//		}
//		
//		return result;
//	}
	
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
