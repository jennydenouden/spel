package nl.vyjy;

public class Windstreek {
	//Enumerations voor het type ondergrond, en het type item
	public static enum Ondergrond {WATER, PAD, EILAND};
	public static enum Item {NIKS, BANAAN, VIS, SCHELP};
	
	private Ondergrond ondergrond;
	private Item item = Item.NIKS;
	
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
}
