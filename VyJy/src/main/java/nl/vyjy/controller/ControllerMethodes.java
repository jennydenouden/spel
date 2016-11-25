package nl.vyjy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.vyjy.Bootje;
import nl.vyjy.BordKolom;
import nl.vyjy.Spel;
import nl.vyjy.Speler;
import nl.vyjy.Tegel;

@Controller
public class ControllerMethodes {

	@Autowired
	private SpelRepository spelRepo;
	@Autowired
	private BootjesRepository bootjesRepo;
	
	@RequestMapping("/bootjesInWinkel")
	public @ResponseBody Iterable<Bootje> getBootjesInWinkel(HttpServletRequest request){
		Spel spel = spelRepo.findOne(getSpelId(request));	
		return spel.getBootjesWinkel().getBootjesTeKoop();
	}
	
	@RequestMapping(value="/koopBootje", method=RequestMethod.POST)
	public @ResponseBody Bootje koopBootje(long id, HttpServletResponse response, HttpServletRequest request) throws IOException{
		Bootje nieuwBootje = null;
		
		Bootje b = bootjesRepo.findOne(id);
		if(b == null || b.isVerkocht()){
			response.sendError(404, "Dat bootje bestaat niet");
			return null;
		}
		else{
			Spel s = spelRepo.findOne(getSpelId(request));
			Speler speler = s.getHuidigeSpeler();
			HttpSession session = request.getSession();
			Object idObjSpeler = session.getAttribute("spelerId");
			if(idObjSpeler != null){
				long spelerId = (long)idObjSpeler;
				if(spelerId == speler.getId()){
					
					if(speler.koopBootje(b)){
						//Werk het bootje bij
						b.setVerkocht(true);
						bootjesRepo.save(b);
						
						//Werk de bootjeswinkel bij
						s.getBootjesWinkel().koopBootje(b);
						nieuwBootje = getEersteOnverkochteBootje(request);
						s.getBootjesWinkel().addBootje(nieuwBootje);
						spelRepo.save(s);
					}
					else{
						response.sendError(404, "Jij kan dit bootje niet betalen");
						return null;
					}
				}
				else{
					response.sendError(404, "Jij bent niet aan de beurt.");
					//System.err.println("id van deze speler: " + spelerId + "\nid van de huidige speler: " + speler.getId());
					return null;
				}
			}
			
			//Return het nieuwe bootje dat in de winkel komt?
			return nieuwBootje;
		}
	}

	@RequestMapping("/tegelsOpBord")
	public @ResponseBody Iterable<BordKolom> getTegelsOpBord(HttpServletRequest request){
		Spel spel = spelRepo.findOne(getSpelId(request));
		return spel.getBord();
	}
	
	/*
	 * Geeft de gespeelde tegel terug als de move succesvol was, anders geeft de methode null
	 */
	@RequestMapping(value  = "/zetTegelOpBord", method = RequestMethod.POST)
	public @ResponseBody Tegel zetTegelOpBord(int kolom, int rij, HttpServletRequest request){
		//Pak maar gewoon de huidige tegel, I guess
		Spel spel = spelRepo.findOne(getSpelId(request));
		Tegel huidigeTegel = spel.getHuidigeTegel();
		Tegel result = null;
		
		if(spel.zetTegel(huidigeTegel, kolom, rij)){;
			List<Tegel> alleTegels = spel.getAlleTegels();
			spel.setHuidigeTegel(alleTegels.get(alleTegels.indexOf(huidigeTegel) + 1));
			spelRepo.save(spel);
			result = huidigeTegel;
		}
		
		return result;
	}
	
	
	
	
	//Methode om het juiste spel uit de database te vissen
	public static long getSpelId(HttpServletRequest request){
		//Default id voor het geval we nog random willen kijken
		long spelId = 1l;
		
		//Vraag het id van het spel op uit de session
		HttpSession session = request.getSession();
		Object idObj = session.getAttribute("spelId");
		if(idObj != null){
			spelId = (long)idObj;
		}
		
		return spelId;
	}
	
	
	//Geeft het eerste onverkochte bootje in de lijst met alle bootjes
	private Bootje getEersteOnverkochteBootje(HttpServletRequest request){
		Spel s = spelRepo.findOne(getSpelId(request));
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
	
}
