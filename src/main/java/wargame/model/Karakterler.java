package wargame.model;

import java.util.Objects;

public class Karakterler {
    private int id;
    private String ad;
    private boolean aktif;

    public Karakterler(int id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karakterler that = (Karakterler) o;
        return id == that.id && Objects.equals(ad, that.ad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ad);
    }

    @Override
    public String toString() {
        return "Karakterler{" +
                "ad='" + ad + '\'' +
                '}';
    }
}
