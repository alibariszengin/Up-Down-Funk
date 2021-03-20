package elevator;

import java.util.ArrayList;
import java.util.Random;

public class LoginThread extends Thread {
    private Avm avm ; // AVM NESNEMIZI TUTMAK ICIN DEGISKENIMIZ
    public LoginThread(Avm avm){
        this.avm=avm;
    } // 1 ARG CONSTRUCYOR
    @Override
    public void run() { // THREAD CALISMA ALANI

        while(true){ // SONSUZA KADAR CALIS
            try { // SLEEP EXCEPTION ATABILIR KAYNAKLI ZORUNLU TRY/CATCH
                Random rand= new Random(); // RANDOM SAYI ALMAK ICIN RANDOM NESNESI OLUSTUR

                int girisMusteriSayisi=rand.nextInt(10)+1 ;// GIRECEK INSAN SAYISI ICIN RASTGELE SAYI AL
                System.out.println("Giren:"+girisMusteriSayisi); // GIREN KISI SAYISI YAZDIR
                for(int i = 0 ; i< girisMusteriSayisi;i++){ // GIRECEK MUSTERI SAYISI KADAR DONDU DONDUR
                    int varisKati = rand.nextInt(4)+1;// RASTGELE KAT BILGISI AL
                    avm.addGirisKuyruk(new Person(varisKati)); // BU KAT BILGISINI HEDEF EDINEN PERSON OLUSTURUP GIRIS
                    // GIRIS KUYRUGUNA EKLE

                }
                avm.girisKuyrukGoster(); // GIRIS KUYRUGUNU UZUNLUGU GOSTER
                Thread.sleep(500); // 500 MILISANIYE UYUT
            } catch (InterruptedException e) { // EXCEPTION YAKALAMA
                e.printStackTrace();
            }

        }

    }
}
