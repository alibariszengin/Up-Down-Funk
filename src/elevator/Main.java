package elevator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // BAŞTA KENDİMİZ KUYRUK OLUSTURUP KATLARA ATIYORUZ,ÇALIŞMAYI KOLAY VE HIZLI KONTROL ICIN
        ArrayList <Person> kuyruk1 = new ArrayList<Person>();
        ArrayList <Person> kuyruk2 = new ArrayList<Person>();
        ArrayList <Person> kuyruk3 = new ArrayList<Person>();
        ArrayList <Person> kuyruk4 = new ArrayList<Person>();
        for(int i = 0; i< 5 ;i++){ // HER KATIN KUYRUGUNA 5 KİŞİ EKLE
            kuyruk1.add(new Person(1,0));
            kuyruk2.add(new Person(2,0));
            kuyruk3.add(new Person(3,0));
            kuyruk4.add(new Person(4,0));

        }

        ArrayList[] kuyruklar={kuyruk1,kuyruk2,kuyruk3,kuyruk4}; // KUYRUKLARI LİSTE İÇİNE AT
        Avm avm = new Avm(kuyruklar); // KUYRUKLARIN OLDUGU LISTESİ AVM CLASS CONSTRUCTOR İÇİNE AT

        ControlThread control= new ControlThread(avm); // CONTROL THREAD NESNE OLUSTUR
        LoginThread loginThread = new LoginThread(avm);// LOGIN THREAD NESNE OLUSTUR
        ExitThread exit = new ExitThread(avm); // EXIT THREAD NESNE OLUSTUR
        loginThread.start();// LOGIN THREAD CALİSTİR
        control.start();// KONTROL THREAD CALİSTİR
        exit.start();// EXİT THREAD CALİSTİR


    }
}
