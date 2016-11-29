package nl.vyjy.websocket;

public class ClientBericht {

	private String content;

    public ClientBericht() {
    }

    public ClientBericht(String content) {
        this.content = content;
    }

    public String getBericht() {
        return content;
    }

    public void setBericht(String bericht) {
        this.content = bericht;
    }
}
