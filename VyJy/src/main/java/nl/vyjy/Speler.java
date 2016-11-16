package nl.vyjy;

import java.util.List;

class Speler {

    private String name;
    private int visjes;
    private int bananen;
    private int schelpen;
    private List<Bootje> inventaris;
    
    public void koopBootje(Bootje bootje){
        boolean betaalbaar = false;
        if(bootje.getPrijsBanaan() <= bananen && bootje.getPrijsSchelp() <= schelpen && bootje.getPrijsVis() <= visjes){
            betaalbaar = true;
        }
        if(betaalbaar && !bootje.isVerkocht()){
            this.inventaris.add(bootje);
            bootje.setVerkocht(true);
        }
    }
    
    public String getName() {
        return name;
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

    public List<Bootje> getInventaris() {
        return inventaris;
    }

    public void setInventaris(List<Bootje> inventaris) {
        this.inventaris = inventaris;
    }
}
