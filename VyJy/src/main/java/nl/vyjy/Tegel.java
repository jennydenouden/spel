package nl.vyjy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class Tegel {

	private long id;
	private boolean gespeeld;
    private String plaatje;       
	
	public Tegel(String plaatje){
		this.plaatje = plaatje;
		this.gespeeld = false;
	}
        
	public Tegel(){
		this.gespeeld = false;
		this.plaatje = "leegvakje.jpg";
	}
	
	@Transient
	public boolean isLegeTegel(){
		return this.plaatje.equals("leegvakje.jpg");
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isGespeeld() {
		return gespeeld;
	}

	public void setGespeeld(boolean gespeeld) {
		this.gespeeld = gespeeld;
	}	

    public String getPlaatje() {
        return plaatje;
    }

    public void setPlaatje(String plaatje) {
        this.plaatje = plaatje;
    }
}
