package nl.vyjy.websocket;

public class ClientBericht {

	private String bericht;

    public ClientBericht() {
    }

    public ClientBericht(String bericht) {
        this.bericht = bericht;
    }

    public String getBericht() {
        return bericht;
    }

    public void setBericht(String bericht) {
        this.bericht = bericht;
    }
}
