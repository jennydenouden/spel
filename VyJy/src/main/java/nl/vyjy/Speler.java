package nl.vyjy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Speler {

    private String name;
    private long id;
    private int visjes;
    private int bananen;
    private int schelpen;
    private List<Bootje> inventaris;
    
    public Speler(String name){
        this.name = name;
        this.visjes = 0;
        this.bananen = 0;
        this.schelpen = 0;
        this.inventaris = new ArrayList<Bootje>();
    }
    
    public Speler(){
    	this.name = "speler" +getId();
    	this.visjes = 0;
    	this.bananen = 0;
        this.schelpen = 0;
        this.inventaris = new ArrayList<Bootje>();
    }
    
    public boolean koopBootje(Bootje bootje){
        boolean betaalbaar = false;
        boolean result = false;
        if(bootje.getPrijsBanaan() <= bananen && bootje.getPrijsSchelp() <= schelpen && bootje.getPrijsVis() <= visjes){
            betaalbaar = true;
        }
        if(betaalbaar && !bootje.isVerkocht()){
            this.inventaris.add(bootje);
            bootje.setVerkocht(true);
            this.bananen -= bootje.getPrijsBanaan();
            this.visjes -= bootje.getPrijsVis();
            this.schelpen -= bootje.getPrijsSchelp();
            result = true;
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

	public String getName() {
        return name;
    }
	
    public void setName(String name) {
		this.name = name;
	}

    public int getVisjes() {
        return visjes;
    }

    public void setVisjes(int visjes) {
        this.visjes = visjes;
    }

    public int getBananen() {
        return bananen;
    }

    public void setBananen(int bananen) {
        this.bananen = bananen;
    }

    public int getSchelpen() {
        return schelpen;
    }

    public void setSchelpen(int schelpen) {
        this.schelpen = schelpen;
    }
    
    @OneToMany
    @JoinColumn(name="speler_id") 
    public List<Bootje> getInventaris() {
        return inventaris;
    }

    public void setInventaris(List<Bootje> inventaris) {
        this.inventaris = inventaris;
    }
}
