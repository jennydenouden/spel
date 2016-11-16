package nl.vyjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BootjesController {

	@Autowired
	private BootjesRepository repo;
	
	@RequestMapping("/bootjes")
	public @ResponseBody String initBootjes(){		
		return "Deze pagina stopt de bootjes in de database";
	}
	
}
