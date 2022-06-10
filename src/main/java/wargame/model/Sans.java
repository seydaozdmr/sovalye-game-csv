package wargame.model;

import java.util.Random;

public class Sans {
    private boolean iyiSans;
    private boolean kotuSans;
    private int sans;

    public void sansAta(Sovalye sovalye){
        int sans = new Random().nextInt(2);
        if(sans==0){
            sovalye.getSans().setIyiSans(false);
        }else{
            sovalye.getSans().setIyiSans(true);
        }
    }

    public void setIyiSans(boolean iyiSans) {
        this.iyiSans = iyiSans;
    }

    public void setKotuSans(boolean kotuSans) {
        this.kotuSans = kotuSans;
    }

    public boolean isIyiSans() {
        return iyiSans;
    }

    public boolean isKotuSans() {
        return kotuSans;
    }

    @Override
    public String toString() {
        return "Sans{" +
                "iyiSans=" + iyiSans +
                ", kotuSans=" + kotuSans +
                ", sans=" + sans +
                '}';
    }
}
