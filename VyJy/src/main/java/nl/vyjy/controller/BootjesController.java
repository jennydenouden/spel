package nl.vyjy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.vyjy.Bootje;

@Controller
public class BootjesController {

	@Autowired
	private BootjesRepository repo;
	
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
			b.setVerkocht(true);
			repo.save(b);
			//repo.delete(b);
			System.err.println("Je moet nog wel even het bootje doorgooien naar een speler");
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
		
		return bootjes;
	}
	
}
