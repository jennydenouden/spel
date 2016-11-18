package nl.vyjy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BordVakje {
    
    private Long id;
    private int xPos;
    private int yPos;
    //private Tegel tegel;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @OneToOne(cascade=CascadeType.ALL, mappedBy="bordvakje")
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

//    public Tegel getTegel() {
//        return tegel;
//    }
//
//    public void setTegel(Tegel tegel) {
//        this.tegel = tegel;
//    }
}
