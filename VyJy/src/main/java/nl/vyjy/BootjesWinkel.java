package nl.vyjy;

import java.util.ArrayList;

public class BootjesWinkel {

	private final int aantalBootjesTeKoop;
	private ArrayList<Bootje> bootjesTeKoop = new ArrayList<>();
	
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
	
	
	
	
	
}
