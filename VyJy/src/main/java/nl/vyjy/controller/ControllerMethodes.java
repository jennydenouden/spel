package nl.vyjy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		
		//Haal het bootje met het gegeven id uit de database
		Bootje b = bootjesRepo.findOne(id);
		if(b == null || b.isVerkocht()){
			//Als het bootje niet bestaat, of al verkocht is,
			//stuur je een error terug
			response.sendError(404, "Dat bootje bestaat niet");
			return null;
		}
		else{
			//Anders: zoek het spel en de huidige speler van dat
			//spel. (Die info komt uit de session.)
			Spel s = spelRepo.findOne(getSpelId(request));
			Speler speler = s.getHuidigeSpeler();
			if(speler.koopBootje(b)){
				//Als de koop succesvol is (De speler heeft voldoende
				//"geld"), werk het bootje bij en sla op in de 
				//database
				b.setVerkocht(true);
				bootjesRepo.save(b);
				
				//Werk de bootjeswinkel bij: haal het verkochte bootje
				//uit de winkel, stop een nieuw onverkocht
				//bootje in de winkel, en sla dat op in de database
				s.getBootjesWinkel().koopBootje(b);
				nieuwBootje = getEersteOnverkochteBootje(request);
				s.getBootjesWinkel().addBootje(nieuwBootje);
				spelRepo.save(s);
			}
			else{
				//Als de speler het bootje niet kan betalen, dan stuurt
				//de server een error terug
				response.sendError(404, "Jij kan dit bootje niet betalen");
				return null;
			}
			
			//Return het nieuwe bootje dat in de winkel komt
			return nieuwBootje;
		}
	}

	@RequestMapping("/tegelsOpBord")
	public @ResponseBody Iterable<BordKolom> getTegelsOpBord(HttpServletRequest request){
		Spel spel = spelRepo.findOne(getSpelId(request));
		
		List<BordKolom> bord = spel.getBord();
		for(BordKolom b : bord){
			List<Tegel> tegels = b.getKolom();
			for(Tegel t : tegels){
				System.err.println(t.getPlaatje());
			}
		}
		
		
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
		
		if(huidigeTegel != null && spel.zetTegel(huidigeTegel, kolom, rij)){;
			List<Tegel> alleTegels = spel.getAlleTegels();
			int nieuweIndexHuidigeTegel = alleTegels.indexOf(huidigeTegel) + 1;
			if(nieuweIndexHuidigeTegel < alleTegels.size()){
				spel.setHuidigeTegel(alleTegels.get(nieuweIndexHuidigeTegel));
				result = huidigeTegel;
			}
			else{
				spel.setHuidigeTegel(null);
			}
			spelRepo.save(spel);
		}
		
		return result;
	}
	

	@RequestMapping(value = "/huidigeTegel")
    public @ResponseBody Tegel getHuidigeTegel(HttpServletRequest request) {
        Spel spel = spelRepo.findOne(getSpelId(request));
        Tegel huidigeTegel = spel.getHuidigeTegel();
        return huidigeTegel;
    }
	
	@RequestMapping("/draaiHuidigeTegel")
	public @ResponseBody Tegel draaiHuidigeTegel(HttpServletRequest request){
		Spel spel = spelRepo.findOne(getSpelId(request));
		Tegel huidigeTegel = spel.getHuidigeTegel();
		huidigeTegel.draaiTegel();
		spelRepo.save(spel);
		return huidigeTegel;
	}

	@RequestMapping("/getHuidigeSpeler")
	public @ResponseBody Speler getHuidigeSpeler(HttpServletRequest request){
		Spel spel = spelRepo.findOne(getSpelId(request));
		return spel.getHuidigeSpeler();
	}
	
	@RequestMapping(value = "/wisselBeurt", method=RequestMethod.POST)
	public @ResponseBody Speler wisselBeurt(HttpServletRequest request){
		Spel spel = spelRepo.findOne(getSpelId(request));
		Speler nieuweSpeler = spel.wisselBeurt();
		spelRepo.save(spel);
		return nieuweSpeler;
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
