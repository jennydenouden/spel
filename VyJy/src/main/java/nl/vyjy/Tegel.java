package nl.vyjy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Random;

@Entity
public class Tegel {

    private long id;
    private boolean gespeeld;
    private String plaatje;
    private int orientatie;
    public static final String prefix = "/images/tegels/";
    private String[] urls;

    public Tegel(String plaatje) {
        this.plaatje = plaatje;
        this.gespeeld = false;
        Random random = new Random();
        int r = random.nextInt(4);
        this.orientatie = r;

        String suffix = plaatje.substring(prefix.length() - 1);

        this.urls = new String[]{prefix + 0 + suffix, prefix + 1 + suffix, prefix + 2 + suffix, prefix + 3 + suffix};
        this.plaatje = urls[orientatie];
    }

    public Tegel() {
        this.gespeeld = false;
        this.plaatje = "/images/tegels/0/leegvakje.jpg";
        this.orientatie = 0;

        this.urls = new String[]{"/images/tegels/0/leegvakje.jpg", "/images/tegels/1/leegvakje.jpg", "/images/tegels/2/leegvakje.jpg", "/images/tegels/3/leegvakje.jpg"};
    }

    @Transient
    @JsonIgnore
    public boolean isLegeTegel() {
        return this.plaatje.equals("/images/tegels/0/leegvakje.jpg");
    }

    public void draaiTegel() {
        orientatie = (orientatie + 1) % 4;
        this.plaatje = urls[orientatie];
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
        //return prefix + orientatie + "/" + suffix;
        this.plaatje = urls[orientatie];
        return plaatje;
    }

    public void setPlaatje(String plaatje) {
        this.plaatje = plaatje;
    }

    public int getOrientatie() {
        return orientatie;
    }

    public void setOrientatie(int orientatie) {
        this.orientatie = orientatie;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}
