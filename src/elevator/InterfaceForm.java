package elevator;

import javax.swing.*;

public class InterfaceForm extends JFrame {
    JTextArea textArea = new JTextArea(); // TEXT DOLU OLAN TEXTAREA DEGISKENI TANIMLAMA
    //CONSTRUCTOR
    public InterfaceForm(String durum) {
        textArea.setText(durum); // ALDIGIMIZ DURUM STRINGINI YAZ ICINE
        textArea.setEditable(false); // KOD DISINDA EDITLENMESINI ENGELLE(KLAVYE ILE)
        add(textArea); // TEXT AREA'YI CERCEVEMIZ ICINE EKLE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // DEFAULT KAPANIS AYARLAMA
        setSize(500,800); // BOYUT AYARLA
    }

    public void setAreaText(String text) // TEXT AREA ICINDEKI TEXT'I DEGISMEK ICIN FONK
    {
        this.textArea.setText(text);
    }
}
