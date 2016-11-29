package nl.vyjy;

import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Tegel {

    private long id;
    private boolean gespeeld;
    private String plaatje;
    private int orientation;

    public Tegel(String plaatje) {
        this.plaatje = plaatje;
        this.gespeeld = false;
        Random random = new Random();
        this.orientation = random.nextInt(4); // een getal tussen 0-3 voor het aantal keren dat de tegel gedraaid moet worden.
    }

    public Tegel() {
        this.gespeeld = false;
        this.plaatje = "/images/tegels/leegvakje.jpg";
        Random random = new Random();
        this.orientation = random.nextInt(4); // een getal tussen 0-3 voor het aantal keren dat de tegel gedraaid moet worden.
    }

    @Transient
    public boolean isLegeTegel() {
        return this.plaatje.equals("/images/tegels/leegvakje.jpg");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public String getPlaatje() {
        return plaatje;
    }

    public void setPlaatje(String plaatje) {
        this.plaatje = plaatje;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
