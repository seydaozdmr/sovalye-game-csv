package wargame.service;

import wargame.model.Dusman;
import wargame.model.Sans;
import wargame.model.Sovalye;

import java.util.ArrayList;

public class OyunVerisi {
    private ArrayList<Sovalye> sovalyeler = new ArrayList<>();
    private ArrayList<Sovalye> aktifSovalyeler = new ArrayList<>();
    private ArrayList<Dusman> dusmanlar = new ArrayList<>();
    private ArrayList<Dusman> aktifDusmanlar = new ArrayList<>();
    private ArrayList<Sans> sanslar = new ArrayList<>();

    public OyunVerisi() {
    }

    public ArrayList<Sovalye> getSovalyeler() {
        return sovalyeler;
    }

    public ArrayList<Sovalye> getAktifSovalyeler() {
        return aktifSovalyeler;
    }

    public ArrayList<Dusman> getDusmanlar() {
        return dusmanlar;
    }

    public ArrayList<Dusman> getAktifDusmanlar() {
        return aktifDusmanlar;
    }

    public ArrayList<Sans> getSanslar() {
        return sanslar;
    }

    public Sovalye getSovalye(String ad){
        //TODO
        Sovalye tempSovalye=null;
        for(Sovalye sovalye:aktifSovalyeler){
            if(sovalye.getAd().equals(ad)){
                tempSovalye=sovalye;
            }
        }
        if(tempSovalye==null)
            System.out.println("ismi girilen şövalye bulunamamıştır.");
        return tempSovalye;
    }
}
