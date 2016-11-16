package nl.vyjy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.vyjy.Bootje;

@Controller
public class BootjesController {

	@Autowired
	private BootjesRepository repo;
	
	@RequestMapping("/bootjes")
	public String initBootjes(Model model){		
		repo.deleteAll();
		ArrayList<Bootje> bootjes = getAllBootjes();
		for(Bootje b : bootjes){
			repo.save(b);
		}
		
		model.addAttribute("bootjes", bootjes);
		return "showBootjes";
	}
	
	
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
