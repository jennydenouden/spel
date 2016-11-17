package nl.vyjy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Spel {
	
	private List<Speler> spelers;
	private List<Tegel> alleTegels;
	private List<Bootje> alleBootjes;
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

	@OneToMany
	@JoinColumn(name = "spel_id")
	public List<Speler> getSpelers() {
		return spelers;
	}

	public void setSpelers(List<Speler> spelers) {
		this.spelers = spelers;
	}

	@OneToMany
	@JoinColumn(name = "spel_id")
	public List<Tegel> getAlleTegels() {
		return alleTegels;
	}

	public void setAlleTegels(List<Tegel> alleTegels) {
		this.alleTegels = alleTegels;
	}

	@OneToMany
	@JoinColumn(name = "spel_id")
	public List<Bootje> getAlleBootjes() {
		return alleBootjes;
	}

	public void setAlleBootjes(List<Bootje> alleBootjes) {
		this.alleBootjes = alleBootjes;
	}
	
}
