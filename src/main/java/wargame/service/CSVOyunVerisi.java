package wargame.service;

import wargame.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVOyunVerisi extends OyunVerisi{

    public void kayitVerileriniYukle() throws IOException {
        //String line="";
        String splistBy=",";
        List<Karakterler> karakterlers= createRandomKarakterler();
        File file=new File("data.csv");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file))){
            StringBuilder builder = new StringBuilder();
            for(Karakterler karakter:karakterlers){
                builder.append(karakter.getId());
                builder.append(splistBy);
                builder.append(karakter.getAd());
                builder.append(splistBy);
                if(karakter instanceof ZayifSovalye){
                    builder.append("Zayıf Şövalye");
                }else if(karakter instanceof GucluSovalye){
                    builder.append("Güçlü Şövalye");
                }else if(karakter instanceof ZayifDusman){
                    builder.append("Zayıf Düşman");
                }else if(karakter instanceof GucluDusman){
                    builder.append("Güçlü Düşman");
                }
                builder.append("\n");
            }
            bufferedWriter.write(builder.toString());
        }
    }
    public void oyunVerileriniYukle(){
        List<Sovalye> sovalyeler = new ArrayList<>();
        List<Dusman> dusmanlar=new ArrayList<>();

        String delimiter=",";
        String line=" ";
        try(BufferedReader reader=new BufferedReader(new FileReader(new File("data.csv")))){
            String [] temp;
            while((line=reader.readLine())!=null){
                temp=line.split(delimiter);
                    if(temp[2].equals("Zayıf Şövalye")){
                        Sovalye zayif=new ZayifSovalye(Integer.parseInt(temp[0]),temp[1]);
                        sovalyeler.add(zayif);
                    }else if(temp[2].equals("Güçlü Şövalye")){
                        Sovalye guclu=new GucluSovalye(Integer.parseInt(temp[0]),temp[1]);
                        sovalyeler.add(guclu);
                    }else if(temp[2].equals("Zayıf Düşman")){
                        Dusman zayif=new ZayifDusman(Integer.parseInt(temp[0]),temp[1]);
                        dusmanlar.add(zayif);
                    }else if(temp[2].equals("Güçlü Düşman")){
                        Dusman guclu=new GucluDusman(Integer.parseInt(temp[0]),temp[1]);
                        dusmanlar.add(guclu);
                    }
            }
            System.out.println("Oyun bilgileri csv dosyasından bulunmuştur");
            this.getDusmanlar().addAll(dusmanlar);
            this.getSovalyeler().addAll(sovalyeler);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Karakterler> createRandomKarakterler(){
        List<Karakterler> karakterler = new ArrayList<>();

        for(int i=0;i<10;i++){
            Sovalye sovalye=new ZayifSovalye(2,"");
            sovalye.setVurusHakki(1);
            sovalye.setAktif(false);
            karakterler.add(sovalye);

        }
        for(int i=0;i<5;i++){
            Sovalye sovalye=new GucluSovalye(1,"");
            sovalye.setVurusHakki(1);
            sovalye.setAktif(false);
            karakterler.add(sovalye);
        }


        for(int i=0;i<5;i++){
            Dusman dusman=new ZayifDusman(2,"");
            dusman.setAktif(false);
            karakterler.add(dusman);
        }
        for(int i=0;i<5;i++){
            Dusman dusman=new GucluDusman(1,"");
            dusman.setAktif(false);
            karakterler.add(dusman);
        }
        return karakterler;
    }
}
