package nl.vyjy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class BordVakje {
    
    private Long id;
    private int xPos;
    private int yPos;
    private Tegel tegel;

    @Id
    @GeneratedValue(generator="myGenerator")
    @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="employee", name = "property")) 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="tegel_id")
    public Tegel getTegel(){
        return tegel;
    }
    public void setTegel(Tegel tegel){
        this.tegel = tegel;
    }
    
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
