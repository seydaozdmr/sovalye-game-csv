package wargame.service;

import wargame.model.Dusman;
import wargame.model.Sans;
import wargame.model.Sovalye;
import wargame.model.ZayifSovalye;

import java.util.ArrayList;
import java.util.Scanner;

public class OyunGorunumu {
    private OyunVerisi oyunVerisi;
    private SavasMotoru savasMotoru;
    private int secim;

    public OyunGorunumu(OyunVerisi oyunVerisi) {
        this.oyunVerisi=oyunVerisi;
        savasMotoru=new SavasMotoru(oyunVerisi);
    }

    public void baslangicEkrani(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("********************************\n");
        stringBuilder.append("Oyuna Hoşgeldiniz\n");
        stringBuilder.append("********************************\n");
        stringBuilder.append("ANA MENÜ\n");
        stringBuilder.append("1-Tüm şövalyeleri göster\n");
        stringBuilder.append("2-Aktif sövalyeleri göster\n");
        stringBuilder.append("3-Adı girilen sövalye bilgilerini göster\n");
        stringBuilder.append("4-Adı girilen şövalyeyi değiştir\n");
        stringBuilder.append("5-Yardım\n");
        stringBuilder.append("6-Oyunu başlat\n");
        stringBuilder.append("7-Oyundan çık\n");
        stringBuilder.append("Lütfen seçiminizi yapınız\n");
        System.out.println(stringBuilder.toString());
    }

    public void anaMenuyuGoster(){
        baslangicEkrani();

    }
    public void yardim(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("********************************\n");
        stringBuilder.append("YARDIM\n");
        stringBuilder.append("********************************\n");
        stringBuilder.append("Ana menüden tüm şövalyeleri görebilir, aktif şövalyeleri görebilirsiniz (1 ve 2)\n");
        stringBuilder.append("Belirli bir ismi girilen şövalyeyi görmek için Ana menüden (3)'e basınız\n");
        stringBuilder.append("Adı girilen şövalyeyi eğer 1'den fazla güçlü şövalyeniz varsa 2 zayıf şövalye ile\n");
        stringBuilder.append(" değiştirebilirsiniz (4) \n");
        stringBuilder.append("Ana menüden (6)'ya basarak oyunu başlatabilirsiniz.\n");
        stringBuilder.append("Oyuncu çıkmadan ya da aktif şövalye sayısı düşman sayısından daha az olmadıkça\n");
        stringBuilder.append("oyun bitmez. \n");
        stringBuilder.append("Oyundan çıkmak için Ana menüden (7)'ye basınız.\n");
        stringBuilder.append("Yardımdan çıkmak için 0 basınız.\n");
        System.out.println(stringBuilder.toString());
    }

    public void sovalyeleriGoster(){
        StringBuilder builder=new StringBuilder();
        builder.append("ŞÖVALYELER : \n");
        builder.append("************\n");
        for(int i=0;i<oyunVerisi.getSovalyeler().size();i++){
            builder.append(oyunVerisi.getSovalyeler().get(i).toString()+"\n");
            builder.append("************\n");
        }
        System.out.println(builder.toString());

    }
    public void aktifSovalyeleriGoster()  {
        StringBuilder builder=new StringBuilder();
        builder.append("AKTİF ŞÖVALYELER : \n");
        builder.append("************\n");
        for(int i=0;i<oyunVerisi.getAktifSovalyeler().size();i++){
            builder.append(oyunVerisi.getAktifSovalyeler().get(i).toString()+"\n");
            builder.append("************\n");
        }
        System.out.println(builder.toString());
    }
    public void menudenSecimYap(){
        Scanner sc=new Scanner(System.in);
        this.secim=sc.nextInt();
    }

    public void oyunDurumunuGoster(ArrayList<Dusman> dusmanlar, ArrayList<Sovalye> sovalyeler){
        StringBuilder builder=new StringBuilder();
        builder.append("************\n");
        builder.append("ŞÖVALYELER VE DÜŞMANLAR : \n");
        builder.append("************\n");
        for(int i=0;i<oyunVerisi.getAktifSovalyeler().size();i++){
            builder.append((i+1)+") *********************");
            builder.append(oyunVerisi.getAktifSovalyeler().get(i).toString()+"\n");
            builder.append("************\n");
        }
        for(int i=0;i<oyunVerisi.getAktifDusmanlar().size();i++){
            builder.append((i+1)+") *********************\n");
            builder.append("Düşman Adı :"+oyunVerisi.getAktifDusmanlar().get(i).toString()+"\n");
            builder.append("************\n");
        }
        System.out.println(builder.toString());
    }

    public void adiGirilenSovalyeGoster(){
        System.out.println("Lütfen istediğiniz şövalyenin adını giriniz");
        String name= new Scanner(System.in).nextLine();
        System.out.println(oyunVerisi.getSovalye(name));
    }

    public int getSecim() {
        return this.secim;
    }

    public void adiGirilenSovalyeyiDegistir() {
        System.out.println("Lütfen istediğiniz şövalyenin adını giriniz");
        String name= new Scanner(System.in).nextLine();
        Sovalye temp= oyunVerisi.getSovalye(name);
        int gucluSayisi=0;
        for(Sovalye sovalye:oyunVerisi.getAktifSovalyeler()){
            if(sovalye.getSovalyeTipi().equals("Güçlü Şövalye")){
                gucluSayisi++;
            }
        }
        //TODO find temp's id
        if(gucluSayisi>1 && temp.getSovalyeTipi().equals("Güçlü Şövalye")){
            int count=0;
            for(int i=0;i<oyunVerisi.getAktifSovalyeler().size();i++){
                if(!oyunVerisi.getAktifSovalyeler().get(i).equals(temp)){
                    count++;
                }else{
                    break;
                }
            }
            Sovalye temp1 = new ZayifSovalye(2,"Şövalye S"+temp.getAd().charAt(temp.getAd().indexOf('S')+1));
            temp1.setAktif(true);
            temp1.setVurusHakki(1);
            temp1.setSovalyeTipi("Zayif Şövalye");
            temp1.setSans(new Sans());
            oyunVerisi.getAktifSovalyeler().set(count,temp1);

            Sovalye temp2 =new ZayifSovalye(2,"Şövalye S6");
            temp2.setAktif(true);
            temp2.setVurusHakki(1);
            temp2.setSovalyeTipi("Zayif Şövalye");
            temp2.setSans(new Sans());
            oyunVerisi.getAktifSovalyeler().add(temp2);
            System.out.println("güçlü şövalye, 2 adet zayıf şövalye ile değiştirildi");
        }

    }

    public SavasMotoru getSavasMotoru() {
        return savasMotoru;
    }
}
