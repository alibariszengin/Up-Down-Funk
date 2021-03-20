package elevator;

import java.util.ArrayList;

public class AsansorThread extends Thread{
    private int id; // ASANSOR ID
    private int asansorKat=0; // ASANSOR BULUNDUGU KAT
    private int varisKat; // ASANSOR HEDEF KAT
    private int yolcuSayisi=0; // ASANSOR ICI YOLCU SAYISI
    private boolean gidisat; // ASANSOR GIDIS YONU
    private boolean aktif=false;// ASANSOR CALISIYOR MU ?
    private int kapasite=10; // ASANSOR KAPASİTE
    private Avm avm; // AVM CLASS'INDAN NESNEMİZİ ATAYACAGIMIZ DEGİSKENİMİZ

    private ArrayList<Person> asansordekiler = new ArrayList<Person>(); // ASANSORDEKİ PERSON NESNELERİNİ TUTAN ARRAYLİST

    public AsansorThread() { // ASANSOR NO ARGUMAN CONSTRUCTOR
        id=-1;
        asansorKat=0;
        varisKat=0;
        yolcuSayisi=0;
        gidisat=true;
    }
    public AsansorThread(int id) { // ASANSOR ONE PARAMETER(ID) CONSTRUCTOR
        gidisat=true;
        this.id=id;
    }

    public AsansorThread(int id , Avm avm){ // ASANSOR 3THREE PARAMETER CONSTRUCTOR ==> GENEL OLARAK KULLANDIGIMIZ
        this.varisKat =0;
        gidisat=true;
        this.id=id;
        this.avm=avm;
    }// CONSTRUCTORLAR

    // GETTER SETTERLAR
    public ArrayList<Person> getAsansordekiler() {
        return asansordekiler;
    }

    public void setAsansordekiler(ArrayList<Person> asansordekiler) {
        this.asansordekiler = asansordekiler;
    }

    public int  getAsansorKat() {
        return asansorKat;
    }

    public void setAsansorKat(int asansorKat) {
        this.asansorKat = asansorKat;
    }

    public int getVarisKat() {
        return varisKat;
    }

    public void setVarisKat(int varisKat) {
        this.varisKat = varisKat;
    }

    public int getYolcuSayisi() {
        return yolcuSayisi;
    }

    public void setYolcuSayisi(int yolcuSayisi) {
        this.yolcuSayisi = yolcuSayisi;
    }

    public boolean isGidisat() {
        return gidisat;
    }

    public void setGidisat(boolean gidisat) {
        this.gidisat = gidisat;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }




    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }
    // THREAD CALİSMA KİSMİ
    @Override
    public void run() {
        this.aktif=true; // ASANSOR AKTİF DEGİSKENİNİ TRUE YAP
        while(!Thread.interrupted()){ // KESİNTİYE UGRAMADIGI SURECE DEVAM ET

            try { // SLEEP HATA FIRLATABİLİR DİYE COMPILER TIME ZAMANINDA ZORUNLU KILINAN TRY/CATCH

                int yon= gidisat ? 1:-1; // GİDİSAT TRUE İSE YUKARI / FALSE İSE ASAGI ==> TRUE =1/FALSE =-1 ==>İNT' OLARAK DEĞER TUT

                for(int i=0;i<4;i++){ // KATLAR ARASI GECİS ICIN 4 KERE DONEN DONGU( EX:0=>1 - 1=>2 - 2=>3 - 3=>4 / 4=>3 - 3=>2 - 2=>1 - 1=>0 )
                    varisKat=varisKat+yon;
                    int asansoreAl; // ASANSORE ALINABILECEK KISIYI TUTACAK DEGISKEN
                    if(!gidisat){ // EGER GIDISAT FALSE ISE (ASAGI DOGRU) BU BLOGA GİR

                        if(getAsansorKat()==4){ // EGER 4. KATTAYSAK ONCE ASANSORDEKİ YOLCULARI BOSALT

                            avm.setKattakiler(yolcuSayisi,3); // KATTAKI KISI SAYISINA ASANSORDEKILERI EKLE
                            asansordekiler.clear(); // ASANSORDEKI PERSONLARI TUTAN ARRAYLIST'I SIFIRLA
                            yolcuSayisi=0; // YOLCU SAYISI DEGISKENINI 0'LA

                        }
                        // ASANSORE KAC KISI ALINACAGINA BAK (EX 3 KISI KUYRUKTA, ASANSORDE 2 KISILIK YER VAR ==> 2 KISI AL)
                        //(EX 2: 8 KISI KUYRUKTA,ASANSORDE 10 KISILIK YER VAR ==> 8 KISIYI AL)
                        asansoreAl = Math.min(avm.getKuyruklar()[3 - i].size(), (kapasite - yolcuSayisi));



                        for(int j = 0 ; j < asansoreAl;j++){ // YOLCULARI DOLDURMAK ICIN ALINACAK KISI SAYISI KADAR DONGU DONDUR
                            asansordekiler.add(avm.kuyrukIndir(3-i)); // ASANSORDKILERE, KUYRUKTAN CIKARDIGIMIZ KISIYI EKLE
                            yolcuSayisi++; // ASANSORDEKI YOLCU SAYISINI 1 ARTTIR
                            avm.setKattakiler(-1,3-i);// KATTAKI KISI SAYISINI 1 AZALT
                        }
                        // BU ISLEMI ALINACAK KISI SAYISI KADAR YAPARAK HERKESI ALMIS OLUYORUZ

                    }
                    else{ // EGER GIDISAT TRUE(YUKARI DOGRU) ISE BU BLOGA GIR
                        if(getAsansorKat()==0){ // EGER 0. KATTAYSAK BU BLOGA GIR
                            if(!(asansordekiler.isEmpty())){ // EGER ASANSOR ICINDE INSAN VARSA
                                asansordekiler.clear(); // ASANSORDEKILER LISTINI SIFIRLA
                                avm.setCikanKisi(yolcuSayisi);
                                yolcuSayisi=0; // YOLCU SAYISINI SIFIR YAP
                            }
                            // VE DOLDURMA ISLEMINI GIRIS KAT ICIN YAP , ONCELIKLE ALINACAK KISI SAYISI HESAPLA
                            asansoreAl= avm.getGirisKuyruk().size() > kapasite-yolcuSayisi ? kapasite-yolcuSayisi:avm.getGirisKuyruk().size();
                            for(int j = 0 ; j < asansoreAl;j++){ // BU INSANLARI ASANSORE EKLE
                                asansordekiler.add(avm.girisBindir()); // VE GIRIS KUYRUGUNDAN CIKAR EKLERKEN
                                yolcuSayisi++; // ASANSOR ICI YOLCU SAYISINI 1 ARTTIR
                            }
                        }
                        else{ // EGER 0. KATTA DEGİLSEK VE YUKARI DOGRU CIKIYORSAK

                            for(int k = 0 ; k < asansordekiler.size();k++){ // ASANSORDEKI KISI SAYISI KADAR DONGU CEVIR
                                if(asansordekiler.get(k).getVarisNoktasi()==getAsansorKat()){// EGER BULUNDUGUMUZ KAT,VARIS NOKTASI OLAN VARSA
                                    asansordekiler.remove(k); // O PERSON NESNESINI ASANSORLER LISTINDEN SIL
                                    yolcuSayisi--; // ASANSOR YOLCU SAYISINI BIR AZALT
                                    avm.setKattakiler(1,getAsansorKat()-1); // KATTAKI INSAN SAYISINI 1 ARTTIR
                                }
                            }

                        }
                    }


                    System.out.println("Asansor "+ id + " hareket ediyor.\t Guncel pozisyon"+ getAsansorKat());
                    Thread.sleep(200); // 200 MILISANIYE UYUT
                    setAsansorKat(getAsansorKat()+yon); // ASANSOR YENI KAT BILGISINI GIR

                }
                gidisat=!(gidisat); // 4 DONUS SONUNDA (0 ==> 4 OR 4==> 0 SONUNDA GIDISAT TARAFINI DEGİS)
                // (EX : GIDISAT= TRUE ==> YON =1 ==> YUKARI CIKIYORUZ ---- KAT 0 - SLEEP 200- KAT 1 -SLEEP 200 -KAT  2 -SLEEP 200
                // KAT 3 - SLEEP 200 - KAT 4 ==> ARTIK ASAGI YONELECEGIZ GIDISAT = FALSE OLMALI !
                //varisKat=varisKat-(yon*4); // VARIS KATINI 4. KATTAYSAK 0'A,0. KATTAYSA 4'E CEVİR
            } catch (InterruptedException e) { // EXCEPTIN FIRLATIRSA YAKALA
                e.printStackTrace();
            }
        }

    }

    public String asansorDurum(){ // ASANSOR DURUMUNU DONDUR


        return "\nAktif : "+aktif+
                "\n\tKat :"+asansorKat+"\n" +
                "\tVaris Kati :"+varisKat+"\n" +
                "\tYon : "+gidisat+"\n" +
                "\tKapasite : "+kapasite+"\n" +
                "\tAsansordeki Yolcu Sayisi :"+yolcuSayisi+"\n" ;
    }

}
