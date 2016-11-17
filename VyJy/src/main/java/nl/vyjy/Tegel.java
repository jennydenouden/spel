package nl.vyjy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tegel {

	private Windstreek noord, oost, zuid, west;
	private long id;
	
	public Tegel(Windstreek noord, Windstreek oost, Windstreek zuid, Windstreek west){
		this.noord = noord;
		this.oost = oost;
		this.zuid = zuid;
		this.west = west;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
