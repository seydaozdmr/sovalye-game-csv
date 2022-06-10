package wargame.service;

import wargame.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OyunYoneticisi {
    OyunGorunumu oyunGorunumu;
    CSVOyunVerisi oyunVerisi;

    public OyunYoneticisi() {
        oyunVerisi=new CSVOyunVerisi();
        this.oyunGorunumu = new OyunGorunumu(oyunVerisi);

        try {
            oyunVerisi.kayitVerileriniYukle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OyunYoneticisi oyunYoneticisi=new OyunYoneticisi();
        //TODO veriler dışarıdan yüklenir
        oyunYoneticisi.oyunVerisi.oyunVerileriniYukle();
        oyunYoneticisi.sovalyeleriAktiflestir();
        oyunYoneticisi.dusmanlariAktiflestir();

        while(true){
             oyunYoneticisi.oyunGorunumu.baslangicEkrani();
             oyunYoneticisi.oyunGorunumu.menudenSecimYap();
             int secim = oyunYoneticisi.oyunGorunumu.getSecim();
            if(secim==1)
                oyunYoneticisi.oyunGorunumu.sovalyeleriGoster();
            else if(secim==2)
                oyunYoneticisi.oyunGorunumu.aktifSovalyeleriGoster();
            else if(secim==3)
                oyunYoneticisi.oyunGorunumu.adiGirilenSovalyeGoster();
            else if(secim==4)
                oyunYoneticisi.oyunGorunumu.adiGirilenSovalyeyiDegistir();
            else if(secim==5)
                oyunYoneticisi.oyunGorunumu.yardim();
            else if(secim==6)
                oyunuBaslat(oyunYoneticisi);
            else if(secim==7){
                System.out.println("oyun sona erdi");
                break;
            }
            else
                System.out.println("yanlış bir seçim yaptınız");
        }
    }

    private static void oyunuBaslat(OyunYoneticisi oyunYoneticisi) {
        while (oyunYoneticisi.oyunGorunumu.getSavasMotoru().oyunDurumu()) {
            oyunYoneticisi.oyunGorunumu.oyunDurumunuGoster(oyunYoneticisi.oyunVerisi.getAktifDusmanlar(), oyunYoneticisi.oyunVerisi.getAktifSovalyeler());
            System.out.println("Kullanacağınız şövalyenin numarasını giriniz: ");
            int sovalyeNo = new Scanner(System.in).nextInt();
            System.out.println("Hedefteki düşmanın numarasını giriniz: ");
            int dusmanNo = new Scanner(System.in).nextInt();
            //TODO eğer -1 girişmişse oyunu sonlandır.
            if (sovalyeNo == -1 || dusmanNo == -1) {
                oyunYoneticisi.oyunGorunumu.getSavasMotoru().setWinUser(false);
                break;
            }
            oyunYoneticisi.oyunGorunumu.getSavasMotoru().vurusYap(sovalyeNo, dusmanNo);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (oyunYoneticisi.oyunGorunumu.getSavasMotoru().isWinUser()) {
            System.out.println("Tebrikler Oyunu Kazandınız");
        } else {
            System.out.println("Üzgünüm Oyunu Kaybettiniz.");
        }
        //TODO oyun bittiğinde
    }

    public void sovalyeleriAktiflestir(){
        int gucluSovalyeSayisi = getRandomNumberUsingNextInt(1,3);
        List<Sovalye> sovalyeler = oyunVerisi.getSovalyeler();
        List<Sovalye> tempSovalyeler=new ArrayList<>();
        int i=1;
        while(tempSovalyeler.size()<5){
            //okunan verilerin içinden rastgele sovalye seçilir ve aktifleştirilir.
            int random=new Random().nextInt(sovalyeler.size());
            //TODO buradan gelen aynı şövalye iki kere random seçilebiliyor
            Sovalye sovalye = sovalyeler.get(random);
            if(gucluSovalyeSayisi>0 && sovalye instanceof GucluSovalye){
                GucluSovalye gucluSovalye= new GucluSovalye(sovalye.getId(),"Şövalye S"+(i++));
                gucluSovalye.setAktif(true);
                gucluSovalye.setSans(new Sans());
                gucluSovalye.setVurusHakki(1);
                gucluSovalyeSayisi--;
                tempSovalyeler.add(gucluSovalye);
            }else if(sovalye instanceof ZayifSovalye && gucluSovalyeSayisi==0){
                ZayifSovalye zayifSovalye=new ZayifSovalye(sovalye.getId(),"Şövalye S"+(i++));
                zayifSovalye.setAktif(true);
                zayifSovalye.setSans(new Sans());
                zayifSovalye.setVurusHakki(1);
                tempSovalyeler.add(zayifSovalye);
            }
        }

        //oyun verisine yüklenir.
        oyunVerisi.getAktifSovalyeler().addAll(tempSovalyeler);
        sansAta();
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min ;
    }

    public void dusmanlariAktiflestir(){
        //Önce aktif şövalye sayısını bul
        int aktifSovalyeler= oyunVerisi.getAktifSovalyeler().size();
        //TODO önce
        int dusmanSayisi= getRandomNumberUsingNextInt(3,aktifSovalyeler+1);
        int gucluDusmanSayisi = getRandomNumberUsingNextInt(1,3);
        List<Dusman> dusmanlar= oyunVerisi.getDusmanlar();
        List<Dusman> tempDusman=new ArrayList<>();
        while(tempDusman.size()<dusmanSayisi){
            int random=new Random().nextInt(dusmanlar.size());
            Dusman dusman = dusmanlar.get(random);
            if(gucluDusmanSayisi>0 && dusman instanceof GucluDusman){
                dusman.setAktif(true);
                tempDusman.add(dusman);
                gucluDusmanSayisi--;
            }else if(dusman instanceof ZayifDusman){
                dusman.setAktif(true);
                tempDusman.add(dusman);
            }
        }
        oyunVerisi.getAktifDusmanlar().addAll(tempDusman);
    }

    public void sansAta(){
        for(int i=0;i<oyunVerisi.getAktifSovalyeler().size();i++){
            Sovalye sovalye = oyunVerisi.getAktifSovalyeler().get(i);
            sovalye.getSans().sansAta(sovalye);
            if (sovalye.getSans().isIyiSans())
                sovalye.setVurusHakki(sovalye.getVurusHakki()+1);
        }
    }


}
