package nl.vyjy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Spel {
	
	public static final int BORDGROOTTE = 25;
	
	private List<Speler> spelers;
	private List<Tegel> alleTegels;
	private List<Bootje> alleBootjes;
	private BootjesWinkel bootjesWinkel;
	
	private List<BordKolom> bord;
	
	private Speler huidigeSpeler;
	private long id;
	private Tegel huidigeTegel;
	
	public Spel(){
		this.spelers = new ArrayList<>();
		this.alleTegels = new ArrayList<>();
		this.alleBootjes = new ArrayList<>();
		
		//Bord wordt een 25 bij 25 lijst met lege tegels
		this.bord = new ArrayList<>();
		for(int i = 0; i < BORDGROOTTE; i++){
			bord.add(new BordKolom());
		}
	}
	
	/*
	 * Zet de tegel op de aangegeven plek op het bord ALS daar nog geen andere
	 * tegel staat. Als de move succesvol was, geeft hij true terug, anders false.
	 */
	public boolean zetTegel(Tegel tegel, int kolomNr, int rijNr){
		boolean result= false;
		//kijk of er al een tegel op die plek staat:
		if(this.bord.get(kolomNr).getKolom().get(rijNr).isLegeTegel()){
			this.bord.get(kolomNr).add(rijNr, tegel);
			result = true;
		}
		
		return result;
	}
	
	@OneToOne
	@JoinColumn(name = "speler_id")
	public Speler getHuidigeSpeler(){
		return this.huidigeSpeler;
	}
	
	public void setHuidigeSpeler(Speler huidigeSpeler) {
		this.huidigeSpeler = huidigeSpeler;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name = "spel_id")
	public List<Speler> getSpelers() {
		return spelers;
	}

	public void setSpelers(List<Speler> spelers) {
		this.spelers = spelers;
	}

	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name = "spel_id")
	public List<Tegel> getAlleTegels() {
		return alleTegels;
	}

	public void setAlleTegels(List<Tegel> alleTegels) {
		this.alleTegels = alleTegels;
	}

	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name = "spel_id")
	public List<Bootje> getAlleBootjes() {
		return alleBootjes;
	}

	public void setAlleBootjes(List<Bootje> alleBootjes) {
		this.alleBootjes = alleBootjes;
	}

	@OneToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name = "bootjeswinkel_id")
	public BootjesWinkel getBootjesWinkel() {
		return bootjesWinkel;
	}

	public void setBootjesWinkel(BootjesWinkel bootjesWinkel) {
		this.bootjesWinkel = bootjesWinkel;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "spel_id")	
	public List<BordKolom> getBord() {
		return bord;
	}

	public void setBord(List<BordKolom> bord) {
		this.bord = bord;
	}
	
	public static int getBordgrootte() {
		return BORDGROOTTE;
	}
	
	public String toString(){
		String result = "Spel tussen ";
		if(spelers.size()>0){
			for(int i = 0; i < spelers.size()-1; i++){
				result += spelers.get(i).getName() + " & ";
			}
			
			result += spelers.get(spelers.size()-1).getName();
		}
		else{
			result = "Er zijn nog geen spelers in dit spel";
		}
		return result;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "huidige_tegel")
	public Tegel getHuidigeTegel() {
		return huidigeTegel;
	}

	public void setHuidigeTegel(Tegel huidigeTegel) {
		this.huidigeTegel = huidigeTegel;
	}
	
}
