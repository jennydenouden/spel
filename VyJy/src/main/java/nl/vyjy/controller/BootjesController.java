package nl.vyjy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.vyjy.Bootje;
import nl.vyjy.Spel;
import nl.vyjy.Speler;

@Controller
public class BootjesController {

	@Autowired
	private BootjesRepository repo;
	
	@Autowired
	private SpelRepository spelRepo;
	
	@Autowired
	private SpelerRepository spelerRepo;
	
	@RequestMapping("/init")
	public String initGame(){		
		//Maak spel als die er nog niet is
		Spel s = spelRepo.findOne(1l);
		if(s == null){
			s = new Spel();
			spelRepo.save(s);
		}
		
		//Als er nog niet genoeg spelers zijn, vraag
		//om invoer om nieuwe spelers te maken
		if(s.getSpelers().size() == 0){
			ArrayList<Speler> spelers = new ArrayList<>();
			spelers.add(new Speler("Jenny"));
			spelers.add(new Speler("Feia"));
			s.setSpelers(spelers);
			spelerRepo.save(spelers);
			
			s.setHuidigeSpeler(spelers.get(0));
			spelRepo.save(s);
		}
		
		return "redirect:/bootjes";
	}
	
	
	/*
	 * Voegt de bootjes toe aan de database, als ze daar niet
	 * al in zaten. Dit gebeurt wanneer men naar de URL /bootjes
	 * surft. Vervolgens worden de bootjes weergegeven dmv de
	 * showBootjes jsp file.
	 */
	@RequestMapping("/bootjes")
	public String initBootjes(Model model){		
		if(repo.count() == 0){
			ArrayList<Bootje> bootjes = getAllBootjes();
			for(Bootje b : bootjes){
				repo.save(b);
			}
		}		
		model.addAttribute("bootjes", repo.findAll());
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
			}
			else{
				response.sendError(404, "De speler kan dit bootje niet betalen");
				return null;
			}
			return "redirect:/bootjes";
		}
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
