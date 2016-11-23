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

	private long id;
	private boolean gespeeld;
        private String image;
	
        public Tegel(String image){
                this.image = image;
		this.gespeeld = false;
	}
        
	public Tegel(){
		this.gespeeld = false;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isGespeeld() {
		return gespeeld;
	}

	public void setGespeeld(boolean gespeeld) {
		this.gespeeld = gespeeld;
	}	

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
