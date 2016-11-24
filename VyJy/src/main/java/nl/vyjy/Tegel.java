package nl.vyjy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Tegel {

	private Windstreek noord, oost, zuid, west;
	private long id;
	private boolean gespeeld;
	private String plaatje;
	
	public Tegel(Windstreek noord, Windstreek oost, Windstreek zuid, Windstreek west, String plaatje){
		this.noord = noord;
		this.oost = oost;
		this.zuid = zuid;
		this.west = west;
		this.gespeeld = false;
		this.plaatje = plaatje;
	}
	
	public Tegel(String plaatje){
		this.plaatje = plaatje;
		this.gespeeld = false;
	}
	
	//Default constructor voor Tegel, omdat dat moet van hibernate
	public Tegel(){	
		this.gespeeld = false;
		this.plaatje = "/images/leegvakje.jpg";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "noord_id")
	public Windstreek getNoord() {
		return noord;
	}

	public void setNoord(Windstreek noord) {
		this.noord = noord;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "oost_id")
	public Windstreek getOost() {
		return oost;
	}

	public void setOost(Windstreek oost) {
		this.oost = oost;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "zuid_id")
	public Windstreek getZuid() {
		return zuid;
	}

	public void setZuid(Windstreek zuid) {
		this.zuid = zuid;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "west_id")
	public Windstreek getWest() {
		return west;
	}

	public void setWest(Windstreek west) {
		this.west = west;
	}
	
	public String toString(){
		return "[N "+noord+"][O "+oost+"][Z "+zuid+"][W "+west+"]";
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
