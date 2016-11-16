package nl.vyjy;

public class Poppetje {
    private int id;
    private Speler eigenaar;
    private Tegel locatieTegel;
    private Windstreek locatieWind;
    private boolean geplaatst;

    public Speler getEigenaar() {
        return eigenaar;
    }
    
    public Tegel getLocatieTegel() {
        return locatieTegel;
    }

    public void setLocatieTegel(Tegel locatieTegel) {
        this.locatieTegel = locatieTegel;
    }

    public boolean isGeplaatst() {
        return geplaatst;
    }

    public int getId() {
        return id;
    }

    public Windstreek getLocatieWind() {
        return locatieWind;
    }

    public void setLocatieWind(Windstreek locatieWind) {
        this.locatieWind = locatieWind;
    }
}
