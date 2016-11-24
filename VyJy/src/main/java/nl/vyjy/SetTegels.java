package nl.vyjy;

import java.util.ArrayList;
import java.util.Collections;

import nl.vyjy.Windstreek.Ondergrond;

/*
 * Deze klasse is alleen bedoeld om een verzameling terug te geven met alle bestaande tegels in het spel.
 */
public class SetTegels {

	/*
	 * For now een versimpelde lijst met alleen water en paadjes, en niet eens alle opties daaruit.
	 */
	public static ArrayList<Tegel> getAlleTegels(){
		ArrayList<Tegel> alleTegels = new ArrayList<>();
		
		Tegel t;
		//1x pad pad pad pad
		t = new Tegel(new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.BRUG), "images/tegels/bbbb.png");
		alleTegels.add(t);
		
		//3x pad pad pad water
		for(int i = 0; i < 3; i++){
			t = new Tegel(new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.WATER), "images/tegels/bbbw.png");
			alleTegels.add(t);
		}
		
		//5x pad water pad water
		for(int i = 0; i < 5; i++){
			t = new Tegel(new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.WATER), new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.WATER), "images/tegels/bwbw_2.png");
			alleTegels.add(t);
		}
		
		//2x pad water water water
		for(int i = 0; i < 2; i++){
			t = new Tegel(new Windstreek(Ondergrond.BRUG), new Windstreek(Ondergrond.WATER), new Windstreek(Ondergrond.WATER), new Windstreek(Ondergrond.WATER), "images/tegels/bwww.png");
			alleTegels.add(t);
		}
		
		//2x water water water water
		for(int i = 0; i < 2; i++){
			t = new Tegel(new Windstreek(Ondergrond.WATER), new Windstreek(Ondergrond.WATER), new Windstreek(Ondergrond.WATER), new Windstreek(Ondergrond.WATER), "images/tegels/wwww.png");
			alleTegels.add(t);
		}
		
		Collections.shuffle(alleTegels);
		
		
		return alleTegels;
	}
	
}
