package nl.vyjy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Windstreek {
	//Enumerations voor het type ondergrond, en het type item
	public static enum Ondergrond {WATER, PAD, EILAND};
	public static enum Item {NIKS, BANAAN, VIS, SCHELP};
	
	private Ondergrond ondergrond;
	private Item item = Item.NIKS;
	private long id;
	
	/*
	 * Constructor voor een windstreek, met de gegeven ondergrond.
	 * Er wordt geen item toegevoegd.
	 */
	public Windstreek(Ondergrond ondergrond){
		this.ondergrond = ondergrond;
	}
	
	/*
	 * Constructor voor een windstreek, met de gegeven ondergrond,
	 * en het gegeven item.
	 */
	public Windstreek(Ondergrond ondergrond, Item item){
		this(ondergrond);
		this.item = item;
	}
	
	// Default constructor voor windstreek, omdat dat moet van hibernate
	public Windstreek(){}
	
	/*
	 * Getter voor de ondergrond
	 */
	public Ondergrond getOndergrond() {
		return ondergrond;
	}

	/*
	 * Getter voor het item
	 */
	public Item getItem() {
		return item;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOndergrond(Ondergrond ondergrond) {
		this.ondergrond = ondergrond;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public String toString(){
		
		String result = "";
		switch(ondergrond){
		case PAD :
			result = "PAD";
			break;
		case EILAND :
			result = "EILAND";
			break;
		case WATER :
			result = "WATER";
			break;
		default :
			result = "WTF????";		
		}
		
		return result;
	}
}
