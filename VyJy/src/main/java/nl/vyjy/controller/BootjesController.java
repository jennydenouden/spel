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
import org.springframework.web.HttpRequestHandler;
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
	
	@Autowired
	private TegelRepository tegelRepo;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		String result = "login";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("naam") != null){
			result = "redirect:/start";
		}
		
		return result;
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.POST )
	public String checkLogin(String naam, HttpServletRequest request){
		HttpSession s = request.getSession(false);
		if(s == null){
			s = request.getSession();
		}
		
		s.setAttribute("naam", naam);
		
		return "redirect:/start";
	}
	
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
	
	/*
	 * Dit is als je via de form een nieuwe speler maakt (wat je dus eigenlijk niet wil)
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
	 * Dit doe je als je op de spel N link klikt in start
	 */
	@RequestMapping(value = "/spel/{id}/maakspeler", method = RequestMethod.GET)
	public String createPlayer(@PathVariable long id, HttpServletRequest request, Model model){
		
		String result;
		
		HttpSession session = request.getSession();
		String naam = (String) session.getAttribute("naam");
		//Er is geen session: je kunt random mensen toevoegen als je dat wil
		//Je kunt dit niet zien aan session, die krijg je automagisch, maar als
		//de naam null is, zijn ze nog niet bij login geweest
		//
		//Note dit is alleen voor nu, in het uiteindelijke spel wil je dit niet
		//kunnen
		if(naam == null){
			model.addAttribute("gameid", id);
			result = "maakspeler";
		}
		//Er is wel een session, voeg de huidige speler toe aan een spel
		//Mss moet je nog checken of die al in een ander spel zit?/ die speler
		//al bestaat idk.
		else{
			Spel s = spelRepo.findOne(id);
			List<Speler> spelers = s.getSpelers();
			
			Object spelerId = session.getAttribute("spelerId");
			if(spelerId == null){
				//Speler bestaat nog niet
				Speler nieuweSpeler = new Speler(naam);
				spelers.add(nieuweSpeler);
				if(spelers.size() == 1){
					s.setHuidigeSpeler(nieuweSpeler);
				}
				
				spelerRepo.save(nieuweSpeler);
				session.setAttribute("spelerId", nieuweSpeler.getId());
				session.setAttribute("spelId", id);
			}
			
			spelRepo.save(s);
			
			result = "redirect:/bord";
		}
		return result;
	}
	
	
	
	
	
	/*
	 * Voegt de bootjes toe aan de database, als ze daar niet
	 * al in zaten. Dit gebeurt wanneer men naar de URL /bootjes
	 * surft. Vervolgens worden de bootjes weergegeven dmv de
	 * showBootjes jsp file.
	 */
	@RequestMapping("/bootjes")
	public String initBootjes(){	
		return "showBootjes";
	}
	
	@RequestMapping("/bordJenny")
	public String showBord(Model model, HttpServletRequest request){
		//Default id voor het geval we nog random willen kijken
		long spelId = 1l;
		
		//Vraag het id van het spel op uit de session
		HttpSession session = request.getSession();
		Object idObj = session.getAttribute("spelId");
		if(idObj != null){
			spelId = (long)idObj;
		}
		
		
		List<Tegel> alleTegels = spelRepo.findOne(spelId).getAlleTegels();
		
		int index = getIndexHuidigeTegel();
		if(index == 13){
			model.addAttribute("tegelsOpBord", alleTegels);
			model.addAttribute("huidigeTegel", null);
			model.addAttribute("tegelsOpStapel", new ArrayList<Tegel>());
		}
		else{
			List<Tegel> tegelsOpBord = alleTegels.subList(0, index);
			Tegel huidigeTegel = alleTegels.get(index);
			List<Tegel> tegelsOpStapel = new ArrayList<>();
			if(index < alleTegels.size()-1){
				tegelsOpStapel= alleTegels.subList(index+1, alleTegels.size());
			}
			
			model.addAttribute("tegelsOpBord", tegelsOpBord);
			model.addAttribute("huidigeTegel", huidigeTegel);
			model.addAttribute("tegelsOpStapel", tegelsOpStapel);
			
			model.addAttribute("spelId", spelId);
			model.addAttribute("spelers", spelRepo.findOne(spelId).getSpelers());
		}
			
		return "bord";
	}
	
	@RequestMapping(value = "/leg/{id}")
	public String legTegel(@PathVariable long id, HttpServletRequest request){
		Tegel t = tegelRepo.findOne(id);
		t.setGespeeld(true);
		tegelRepo.save(t);
		
		//TODO: Dit is lelijk en nutteloos so far, maar dan kan ik iig iets testen
		Spel s = spelRepo.findOne(ControllerMethodes.getSpelId(request));
		int kolom, rij;
		Random random = new Random();
		kolom = random.nextInt(25);
		rij = random.nextInt(25);
		s.zetTegel(t, kolom, rij);
		spelRepo.save(s);
		
		return "redirect:/bordJenny";
	}
	
	
	/*
	 * Hulpmethode die de index van de huidige tegel (bovenste tegel op 
	 * de stapel tegels) teruggeeft. Hiermee kan de verzameling tegels
	 * worden gesplitst in tegels op het bord, de huidige tegel, en tegels
	 * op de stapel.
	 */
	private int getIndexHuidigeTegel(){
		
		Spel s = spelRepo.findOne(1l);
		List<Tegel> tegels = s.getAlleTegels();
		int result = tegels.size();
		
		for(int i = 0; i < tegels.size(); i++){
			if(!tegels.get(i).isGespeeld()){
				result = i;
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
