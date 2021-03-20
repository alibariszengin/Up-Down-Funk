package elevator;

import java.util.ArrayList;
import java.util.Random;

public class ExitThread extends Thread {

    private Avm avm; // AVM NESNESINI TUTMAK ICIN DEGISKENIMIZ

    public ExitThread(Avm avm) {
        this.avm = avm;
    } // CONSTRUCTOR

    @Override
    public void run() { // THREAD CALISTMA ALANI

        while(true){ // INFINITY LOOP ILE CALIS SONSUZA KADAR
            try {
                Random rand= new Random(); // RANDOM SAYI ALMAK ICIN RANDOM NESNESI OLUSTUR
                Thread.sleep(1000);// 1000 MILISANIYE UYUT
                int cikisMusteriSayisi=rand.nextInt(5)+1; // KATTAN KUYRUGA GECECEK RASTGELE KISI SAYISI AL
                for(int i = 0 ; i< cikisMusteriSayisi;i++){ // KISI SAYISI KADAR DONGUYU DONDUR

                    int cikacakKisiKat = rand.nextInt(4)+1; // CIKACAK KISILERI RASTGELE KATLARDAN AL
                    avm.addKuyruk((cikacakKisiKat-1),(new Person(cikacakKisiKat,0))); // CIKACAK KISININ,
                    // OLDUGU KATTAN KUYRGUA YENI BIR PERSON NESNESI EKLE(KAT BILGISI ILE VARIS NOKTASI = 0 BILGILERI ILE)

                }
            } catch (InterruptedException e) { // EXCEPTION HANDLE ETMEK ICIN TRY/CATCH
                e.printStackTrace();
            }

        }
    }
}
