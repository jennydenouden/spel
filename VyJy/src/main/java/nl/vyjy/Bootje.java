package nl.vyjy;

public class Bootje {

    private long id;
    private int waarde;
    private int prijsBanaan;
    private int prijsVis;
    private int prijsSchelp;
    private Speler eigenaar;

    Bootje(long id, int waarde, int prijsBanaan, int prijsVis, int prijsSchelp){
        // deze class moet met een database gaan werken
        this.id = id;
        this.waarde = waarde;
        this.prijsBanaan = prijsBanaan;
        this.prijsVis = prijsVis;
        this.prijsSchelp = prijsSchelp;
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

    public Speler getEigenaar() {
        return eigenaar;
    }

    public void setEigenaar(Speler eigenaar) {
        this.eigenaar = eigenaar;
    }
}
