package elevator;

public class Person { // PERSON CLASS'I MUSTERILERI TUTMAK ICIN
    private int guncelPozisyon; // GUNCEL KAT BILGISI
    private int varisNoktasi;// VARMAK ISTEDIGI KAT BILGISI

    // CONSTRCUTORLAR
    public Person(int varisNoktasi) {
        this.guncelPozisyon=0;
        this.varisNoktasi = varisNoktasi;
    }

    public Person(int guncelPozisyon, int varisNoktasi) {
        this.guncelPozisyon = guncelPozisyon;
        this.varisNoktasi = varisNoktasi;
    }
    // PERSON NESNELERININ GUNCEL POZISYON VE HEDEF KATINI YAZDIRMAK ICIN / KONTROL ICIN KULLANIMA ACIK FAKAT KULLANILMIYOR
    public void showMeYourInformation(){
        System.out.println("Guncel Pozisyon: "+ guncelPozisyon+
                "\nVaris Kati: "+ varisNoktasi);
    }
    // GETTER SETTERLAR
    public int getGuncelPozisyon() {
        return guncelPozisyon;
    }

    public void setGuncelPozisyon(int guncelPozisyon) {
        this.guncelPozisyon = guncelPozisyon;
    }

    public int getVarisNoktasi() {
        return varisNoktasi;
    }

    public void setVarisNoktasi(int varisNoktasi) {
        this.varisNoktasi = varisNoktasi;
    }
}
