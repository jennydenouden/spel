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

@Entity
public class BootjesWinkel {

	private int aantalBootjesTeKoop;
	private List<Bootje> bootjesTeKoop = new ArrayList<>();
	private long id;
	
	/*
	 * Constructor voor een bootjeswinkel, waarbij je aan kan geven hoeveel
	 * bootjes tegelijk in de winkel kunnen liggen.
	 */
	public BootjesWinkel(int aantalBootjesTeKoop){
		this.aantalBootjesTeKoop = aantalBootjesTeKoop;
	}
	
	/*
	 * Tweede constructor voor een bootjeswinkel, waarbij het aantal bootjes
	 * dat tegelijk in de winkel kan liggen 4 is.
	 */
	public BootjesWinkel(){
		this(4);
	}
	
	/*
	 * Voeg een bootje toe aan de winkel. Dit kan alleen als er plek vrij is 
	 * in de winkel.
	 */
	private void addBootje(){
		if(bootjesTeKoop.size() < aantalBootjesTeKoop){
			Bootje b = null;	//Hier moet je het eerste bootje uit de lijst halen
			bootjesTeKoop.add(b);
		}
	}
	
	/*
	 * Koop een bootje uit de winkel. Je krijgt het bootje dat overeenkomt met de
	 * index die je meegeeft aan de methode. De methode zorgt er ook voor dat je 
	 * bootjeswinkel automatisch weer wordt aangevuld.
	 */
	public Bootje koopBootje(int i){
		Bootje result = bootjesTeKoop.get(i);
		if(result != null){
			addBootje();
		}
		return result;
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
	@JoinColumn(name = "bootjeswinkel_id")
	public List<Bootje> getBootjesTeKoop() {
		return bootjesTeKoop;
	}

	public void setBootjesTeKoop(List<Bootje> bootjesTeKoop) {
		this.bootjesTeKoop = bootjesTeKoop;
	}

	public int getAantalBootjesTeKoop() {
		return aantalBootjesTeKoop;
	}

	public void setAantalBootjesTeKoop(int aantalBootjesTeKoop) {
		this.aantalBootjesTeKoop = aantalBootjesTeKoop;
	}
	
	
	
	
	
}
