package nl.vyjy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BordVakje {
    
    @Id
    private long id;
    private int xPos;
    private int yPos;
    //private Tegel tegel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//    public Tegel getTegel() {
//        return tegel;
//    }
//
//    public void setTegel(Tegel tegel) {
//        this.tegel = tegel;
//    }
}
