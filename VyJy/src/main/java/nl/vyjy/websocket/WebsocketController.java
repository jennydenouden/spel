package nl.vyjy.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;

@Controller
public class WebsocketController {
	
	//Ontvang berichten die worden gestuurd naar /bla,
	//stuur een antwoord op /something
	@MessageMapping("/bla")
	@SendTo("/something")
	public ServerBericht message(ClientBericht bericht) throws Exception{
		//Ik weet nog niet wat hierin moet
		return new ServerBericht("bericht van de server");
	}
		
}
