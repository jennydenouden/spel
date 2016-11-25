package nl.vyjy.websocket;

public class ServerBericht {

	String bericht;
	
	public ServerBericht(){
		
	}
	
	public ServerBericht(String bericht){
		this.bericht = bericht;
	}
	
	public String getBericht(){
		return this.bericht;
	}
	
}
