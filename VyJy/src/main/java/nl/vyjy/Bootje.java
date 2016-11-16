package nl.vyjy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bootje {

    private long id;
    private int waarde;
    private int prijsBanaan;
    private int prijsVis;
    private int prijsSchelp;
    private boolean verkocht;

    Bootje(long id, int waarde, int prijsBanaan, int prijsVis, int prijsSchelp){
        // deze class moet met een database gaan werken
        this.id = id;
        this.waarde = waarde;
        this.prijsBanaan = prijsBanaan;
        this.prijsVis = prijsVis;
        this.prijsSchelp = prijsSchelp;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public int getWaarde() {
        return waarde;
    }

    public int getPrijsBanaan() {
        return prijsBanaan;
    }

    public int getPrijsVis() {
        return prijsVis;
    }

    public int getPrijsSchelp() {
        return prijsSchelp;
    }
    
    public boolean IsVerkocht() {
        return verkocht;
    }

    public void setVerkocht(boolean verkocht) {
        this.verkocht = verkocht;
    }

	public boolean isVerkocht() {
		return verkocht;
	}

	public void setWaarde(int waarde) {
		this.waarde = waarde;
	}

	public void setPrijsBanaan(int prijsBanaan) {
		this.prijsBanaan = prijsBanaan;
	}

	public void setPrijsVis(int prijsVis) {
		this.prijsVis = prijsVis;
	}

	public void setPrijsSchelp(int prijsSchelp) {
		this.prijsSchelp = prijsSchelp;
	}
}
