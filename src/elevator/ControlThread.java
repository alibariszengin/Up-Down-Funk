package elevator;

import java.util.ArrayList;
import java.util.Arrays;

public class ControlThread extends Thread { // THREAD UZERINDEN MIRAS ALIYORUZ 4 TANE THREAD CLASS'IMIZ GIBI
    private ArrayList<AsansorThread> asansorler; // ASANSORLERI TUTTUGUMUZ LIST
    private Avm avm ; // AVM NESNESINI TUTTUGUMZU DEGISKEN
    InterfaceForm durumTablo; // ARAYUZU CIKARMAK ICIN ARAYUZ CLASS'IMIZDAN OLUSMA NESNE REFERANSIMIZ
    private int aktifAsansor=1; // AKTIF ASANSOR SAYISI


    public ControlThread(Avm avm){ // CONSTRUCTOR

        this.avm=avm;
        // ASANSOR THREAD NESNELERIMIZ
        AsansorThread asansor1= new AsansorThread(1,avm);
        AsansorThread asansor2 =new AsansorThread();
        AsansorThread asansor3 =new AsansorThread();
        AsansorThread asansor4 =new AsansorThread();
        AsansorThread asansor5 =new AsansorThread();
        asansorler= new <AsansorThread>ArrayList(); // ASANSOR NESNELERIMIZI TUTAN LIST
        asansorler.addAll(Arrays.asList(asansor1,asansor2,asansor3,asansor4,asansor5));// ICINE ASANSORLERI EKLIYORUZ
        durumTablo=new InterfaceForm(durum()); // ARAYUZ NESNEMIZI CONSTRUCTOR ILE OLUSTURUYOR VE TEXT'I ATIYORUZ
        durumTablo.setVisible(true);// ARAYUZU GORUNUZ KILYIORUZ
        asansorler.get(0).start();// ILK ASANSORU CALISTIRMAYA BASLIYORUZ

    }
    @Override
    public void run() { // THREAD CALISMA ALANI

        while (true){ // SONSUZA KADAR DON

            try { // ZORUNLU TRY/CATCH ALANIMIZ
                if(avm.kuyrukTotalInsan()>=20){ // KUYRUKLARDAKI TOPLAM SAYI 20'DEN FAZLA OLDU İSE
                    for(int i =1;i< asansorler.size();i++){ // ASANSORLER LISTESI ICINDE DON
                        if(!(asansorler.get(i).isAktif())){ // AKTIF OLMAYAN VARSA ( ILKINI BULUYORUZ)
                            asansorler.set(i,new AsansorThread(i+1,avm)); // ONU AKTIVE ET YENI THREAD
                            // NESNESI OLUSTURARAK
                            asansorler.get(i).start(); // VE CALISTIR
                            break; // DONGUDEN CIK ( BU SAYEDE ILK INAKTIF OLANI CALISTIRIP ISLEMI SONLANDIRIYORUZ)
                        }
                    }
                }
                else if(avm.kuyrukTotalInsan()<10){ // EGER KUYRUKLARDAKI TOTAL SAYI 10 ALTINDAYSA
                    for(int i =2;i< asansorler.size();i++){ // ASANSORLERDE DON

                        if ((i == asansorler.size() - 1) && (asansorler.get(i).isAktif())){ // SONUNCUSUNA GELDIYSEK
                            // VE AKTIFSE SONUNCUSU,İNAKTİF ET
                            asansorler.get(i).setAktif(false); // AKTIF DEGISKENI FALSE YAP
                            asansorler.get(i).setYolcuSayisi(0); // YOLCU SAYISINI SIFIRLA
                            durumTablo.setAreaText(durum());// SILMEDEN ONCE GUNCEL TABLOYU YANSIT ARAYUZE
                            asansorler.get(i).stop(); // VE DURDUR
                            break; // BREAK ILE CIK DONGUDEN
                        }
                        else if(!(asansorler.get(i).isAktif())){ // EGER SONUNCU ASANSORE GELENE KADAR AKTIF OLMAYAN BULRUSAK
                            asansorler.get(i-1).setAktif(false); // 1 ONCEKI AKTIFTIR DIYEBILIRIZ SIRAYLA ACTIGIMZI ICIN
                            asansorler.get(i-1).setYolcuSayisi(0);// BIR ONCEKINI INAKTIF EDİP
                            durumTablo.setAreaText(durum());// SON KEZ TABLOYU YANSITIP
                            asansorler.get(i-1).stop();

                            break; // CIKABILIRIZ(BREAK SAYESINDE ILK INAKTIF OLANI BULDUKTAN SONRA ONCEKINI DE INAKTIF
                            // EDIP ISLEMI SONLANDIRABILYIORUZ
                        }
                    }
                }
                for(int i = 0;i<20;i++){ // 10 KERE 50 MİLİSANİYE ARALIKLARLA TABLO TEXTINI GUNCELLE
                    durumTablo.setAreaText(durum()); // TABLO GUNCELLE
                    Thread.sleep(50); // 50 MİLİSANİYE BEKLE
                }
                // DONME SAYISI * SLEEP SURESI KADAR GECTIKTEN SONRA TEKRAR DONGU BASINA DON THREAD CALISMASINA
                // DEVAM ET, BU SAYEDE KONTROLLERI YAPMADAN ONCE ARAYA SURE DE KOYMUS OLUYORUZ
            } catch (InterruptedException e) { // EXCEPTION HANDLER
                e.printStackTrace();
            }


        }


    }
    public String durum(){ // ASANSORLERIN DURUMLARINI YAZDIRMAK ICIN
        String durum=""; // DURUM DIYE STRING OLSUTUR
        // KATLARIN BILGISI ICIN KATLAR DEGISKENINE STRING DEGER ATA
        String katlar="0. kat ==> Queue:"+ avm.getGirisKuyruk().size()+
                "\n1. kat ==> All : "+ avm.getKattakiler()[0]+"\t Queue:"+avm.getKuyruklar()[0].size()+
                "\n2. kat ==> All : "+ avm.getKattakiler()[1]+"\t Queue:"+avm.getKuyruklar()[1].size()+
                "\n3. kat ==> All : "+ avm.getKattakiler()[2]+"\t Queue:"+avm.getKuyruklar()[2].size()+
                "\n4. kat ==> All : "+ avm.getKattakiler()[3]+"\t Queue:"+avm.getKuyruklar()[3].size()+
                "\nExit ==>"+ avm.getCikanKisi()+"\n\n";
        durum=durum.concat(katlar); // VE KATLAR DEGISKENINI BOS OLAN DURUM STRINGINE EKLE
        for(int i =0 ; i < asansorler.size();i++){
            durum=durum.concat(asansorler.get(i).asansorDurum()); // SON OLARAK HER ASANSORUN DURUM BILGISINI
            // DURUM STRINGINE EKLE
        }
        String giristekiler="\n0.floor ==>";
        int[] giristenKatlara={0,0,0,0};
        for(int j= 0 ; j< avm.getGirisKuyruk().size();j++){
            giristenKatlara[avm.getGirisKuyruk().get(j).getVarisNoktasi()-1]++;
        }
        for(int j=0;j< 4 ;j++){
            if(giristenKatlara[j]!=0){
                String girisSiradakiler="["+giristenKatlara[j]+","+(j+1)+"]";
                giristekiler=giristekiler.concat(girisSiradakiler);
            }

        }



        durum=durum.concat(giristekiler);
        String siradakiler="\n1.floor ==> ["+avm.getKuyruklar()[0].size()+",0]\n" +
                "2.floor ==> ["+avm.getKuyruklar()[1].size()+",0]\n" +
                "3.floor ==> ["+avm.getKuyruklar()[2].size()+",0]\n" +
                "4.floor ==> ["+avm.getKuyruklar()[3].size()+",0]\n" ;
        durum=durum.concat(siradakiler);
        return durum; // DURUM STRINGINI ( ARAYUZDEKI YAZIMIZI DONDUR)
    }
}
