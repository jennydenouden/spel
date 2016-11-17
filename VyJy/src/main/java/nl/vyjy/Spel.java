package nl.vyjy;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Spel {
	
	@Transient
	private ArrayList<Speler> spelers;
	@Transient
	private ArrayList<Tegel> alleTegels;
	@Transient
	private ArrayList<Bootje> alleBootjes;
	@Transient
	private BootjesWinkel bootjesWinkel;
	//TODO: Volgt later
	//private Bord bord;
	
	private Speler huidigeSpeler;
	private long id;
	
	public Spel(){
		this.spelers = new ArrayList<>();
		this.alleTegels = new ArrayList<>();
		this.alleBootjes = new ArrayList<>();
		this.bootjesWinkel = new BootjesWinkel(4);
	}
	
	public Speler getHuidigeSpeler(){
		return this.huidigeSpeler;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setHuidigeSpeler(Speler huidigeSpeler) {
		this.huidigeSpeler = huidigeSpeler;
	}
	
}
