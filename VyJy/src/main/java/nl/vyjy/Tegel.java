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
	
	public Tegel(Windstreek noord, Windstreek oost, Windstreek zuid, Windstreek west){
		this.noord = noord;
		this.oost = oost;
		this.zuid = zuid;
		this.west = west;
	}
	
	//Default constructor voor Tegel, omdat dat moet van hibernate
	public Tegel(){	}

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
}
