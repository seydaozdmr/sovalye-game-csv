package wargame.service;

import wargame.model.*;

public class SavasMotoru {
    private OyunVerisi oyunVerisi;
    private boolean isWinUser;

    public SavasMotoru(OyunVerisi oyunVerisi) {
        this.oyunVerisi = oyunVerisi;
    }

    public boolean isWinUser() {
        return isWinUser;
    }

    public void setWinUser(boolean winUser) {
        isWinUser = winUser;
    }

    public boolean oyunDurumu(){
        //TODO eğer aktif düşman sayısı 0'dan büyükse ve aktif sövalye sayısı aktif düşman sayısına büyük eşitse oyun devam eder
        if(oyunVerisi.getAktifDusmanlar().size()>0 && oyunVerisi.getAktifSovalyeler().size()>=oyunVerisi.getAktifDusmanlar().size())
            return true;
        //TODO aktif düşman sayısı 0 ise oyun biter kazanırsın
        else if(oyunVerisi.getAktifDusmanlar().size()==0){
            isWinUser=true;
            return false;
            //TODO aktif düşman sayısı aktif şövalye sayısından büyükse oyun biter ve kaybedersin
        }else if(oyunVerisi.getAktifDusmanlar().size()>oyunVerisi.getAktifSovalyeler().size()){
            isWinUser=false;
            return false;
        }
        return true;
    }

    public void vurusYap(int sovalyeNo, int dusmanNo) {
        Sovalye tempSovalye=oyunVerisi.getAktifSovalyeler().get(sovalyeNo-1);
        if(tempSovalye!=null){
            Dusman tempDusman = oyunVerisi.getAktifDusmanlar().get(dusmanNo-1);
            if(tempDusman!=null){
                if(tempSovalye instanceof GucluSovalye){
                    oyunVerisi.getAktifDusmanlar().remove(tempDusman);
                    tempSovalye.setVurusHakki(tempSovalye.getVurusHakki()-1);
                    if(tempSovalye.getVurusHakki()<=0){
                        oyunVerisi.getAktifSovalyeler().remove(tempSovalye);
                    }
                }else if(tempSovalye instanceof ZayifSovalye && tempDusman instanceof ZayifDusman){
                    oyunVerisi.getAktifDusmanlar().remove(tempDusman);
                    tempSovalye.setVurusHakki(tempSovalye.getVurusHakki()-1);
                    if(tempSovalye.getVurusHakki()<=0){
                        oyunVerisi.getAktifSovalyeler().remove(tempSovalye);
                    }
                }else if(tempSovalye instanceof ZayifSovalye && tempDusman instanceof GucluDusman){
                    oyunVerisi.getAktifSovalyeler().remove(tempSovalye);
                }
            }else{
                System.out.println(dusmanNo+ " numaralı düşman aktif düşmanlar içinde bulunmamaktadır.");
            }
        }else{
            System.out.println(sovalyeNo+" numaralı sövalye aktif şövalyeler içinde bulunmamaktadır.");
        }
    }
}
