package wargame.model;

public class Dusman extends Karakterler{
    private boolean durum;
    private String dusmanTipi;

    public Dusman(int id, String ad) {
        super(id, ad);
    }

    public void setDusmanTipi(String dusmanTipi) {
        this.dusmanTipi = dusmanTipi;
    }

    @Override
    public String toString() {
        return "Dusman{" +
                "***************************+" +
                '}';
    }
}
