package elevator;

import java.util.ArrayList;

public class Avm {
    private ArrayList<Person>[] kuyruklar= new ArrayList[4]; // KATLARIN KUYRUGUNU TUTAN LISTE
    private ArrayList<Person> girisKuyruk= new ArrayList<Person>(); // GIRIS KUYRUGUNU TUTAN LISTE
    private int[] kattakiler={100,100,100,100} ; // DEFAULT OLARAK HER KATTA 100 KISI OLDUGUNU VARSAYALIM
    // SYNCHRONİZED ILE HER FONKSIYONA TEK SEFERDE BIR THREADIN ULASMASINI SAGLIYORUZ
    private int cikanKisi=0;
    public Avm() {

    }

    public int getCikanKisi() {
        return cikanKisi;
    }

    public void setCikanKisi(int cikanKisi) {
        this.cikanKisi=this.cikanKisi+cikanKisi;
    }

    public Avm(ArrayList<Person>[] kuyruklardakiInsan) {

        this.kuyruklar = kuyruklardakiInsan;
    }// CONSTRUCTORLAR

    // GETTER SETTERLAR
    public synchronized ArrayList<Person>[] getKuyruklar() {
        return kuyruklar;
    }

    public void setKattakiler(int[] kattakiler) {
        this.kattakiler = kattakiler;
    }

    public void setKuyruklar(ArrayList<Person>[] kuyruklar) {
        this.kuyruklar = kuyruklar;
    }

    public synchronized ArrayList<Person> getGirisKuyruk() {
        return girisKuyruk;
    }

    public void setGirisKuyruk(ArrayList<Person> girisKuyruk) {
        this.girisKuyruk = girisKuyruk;
    }

    // KUYRUKLARIN BILGILERINI ( KAT ILE KATTAKI KUYRUKLARDAKI INSANLARIN VARIS NOKTALARINI YAZDIRAN FONSIYON
    // KONTROL AMACLI ,KULLANMIYORUZ KODUMZUDA
    public void kuyruklariGoster(){
        for(int i = 0;i<4;i++){
            System.out.println("Kat: "+ i);
            for(int j=0;j< kuyruklar[i].size();j++){

                System.out.print("-"+(kuyruklar[i].get(j).getVarisNoktasi()));
            }
        }
    }
    public synchronized void setKattakiler(int ekle,int kat) { // ISTENILEN KATA ISTENILEN SAYIDA INSAN EKLE
        this.kattakiler[kat]+=ekle;
    }

    public void girisKuyrukGoster(){
        System.out.println("Giris kat kuyruk uzunlugu: "+girisKuyruk.size());
    } // GIRIS KAT UZUNLUGU YAZDIR
    public int kuyrukTotalInsan(){ // KUYRUKLARDAKI TOPLAN INSAN SAYISINI DONDUR
        int toplam=0;
        for(int i =0;i<kuyruklar.length;i++){ // KUYRUK SAYISI KADAR DONGUYU DONDUR
            toplam=toplam+kuyruklar[i].size(); // HER KUYRUKTAKI PERSON SAYISINI EKLE
        }
        toplam=toplam+girisKuyruk.size(); // EN SON OLARAK GIRIS KAT KUYRUKTAKI PERSON SAYISINI EKLE

        return toplam; // SONUCU DONDUR
    }
    public synchronized Person kuyrukIndir(int kat){ // VERILEN KAT KUYRUGUNDA EN ONDEKI KISIYI SIL VE DONDUR

        return (kuyruklar[kat].remove(0));

    }

    public int[] getKattakiler() { // KATLARDAKI KISI SAYILARINI TUTAN DIZIYI DONDUR
        return kattakiler;
    }

    public synchronized Person girisBindir(){

        return girisKuyruk.remove(0);

    }// GIRIS KUYRUGUNUN EN ONUNDEKI KISIYI SIL VE DONDUR

    public synchronized void addGirisKuyruk(Person person) {
        this.girisKuyruk.add(person);
    } // GIRIS KUYRUGU SONUNA VERILEN PERSON NESNESINI EKLE
    public synchronized void  addKuyruk(int kat, Person person){
        kuyruklar[kat].add(person); //VERILEN KATTAKI KUYRUK SONUNA VERILEN PERSON NESNESINI EKLE
    }
}
