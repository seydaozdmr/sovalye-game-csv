package wargame.model;

public class Sovalye extends Karakterler{
    private boolean durum;
    private int vurusHakki;
    private Sans sans;
    private String sovalyeTipi;

    public Sovalye(int id, String ad) {
        super(id, ad);
    }

    public int getVurusHakki() {
        return vurusHakki;
    }

    public void setVurusHakki(int vurusHakki) {
        this.vurusHakki = vurusHakki;
    }

    public void vur(Dusman dusman){

    }

    public Sans getSans() {
        return sans;
    }

    public void setSans(Sans sans) {
        this.sans = sans;
    }

    public void setSovalyeTipi(String sovalyeTipi) {
        this.sovalyeTipi = sovalyeTipi;
    }

    public String getSovalyeTipi() {
        return sovalyeTipi;
    }


    @Override
    public String toString() {
        return "Sovalye{" +
                "adi= "+getAd()+
                ", id=" + getId() +
                ", vurusHakki=" + vurusHakki +
                ", sans=" + sans +
                ", sovalyeTipi='" + sovalyeTipi + '\'' +
                '}';
    }
}
