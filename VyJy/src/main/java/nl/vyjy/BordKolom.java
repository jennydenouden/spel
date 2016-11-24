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
public class BordKolom {
	private List<Tegel> kolom;
	private long id;
	
	public BordKolom(){
		//Vul kolom met lege tegels
		this.kolom = new ArrayList<>();	
		for(int i = 0; i < Spel.BORDGROOTTE; i++){
			this.kolom.add(new Tegel());
		}
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "kolom_id")
	public List<Tegel> getKolom() {
		return kolom;
	}

	public void setKolom(List<Tegel> kolom) {
		this.kolom = kolom;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void add(int rijNr, Tegel tegel) {
		this.kolom.set(rijNr, tegel);
		
	}
}
