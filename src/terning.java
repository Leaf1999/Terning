package terning;

import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class terning extends PApplet {

    //angiver variabler og arrays
    int textSize = 20;
    int terning [] = new int [5];// antallet af terninger
    int result [] = new int [6];// antallet af resultater
    int IntMelin = 50; //interval mellem linjer

    //kommandoer man starter før loop
    public void setup() {
        background(0);
        fill(255);

        textSize(textSize);
    }
    // Loop, som fungere som et for-loop
    public void draw () {
        for (int i = 0; i < terning.length; i++) {
            terning [i] = (int) random(1, 7); // angiver værdierne 1-6 til alle terninger
        }
    }

    // Slår terningerne ved gamebegin
    public void gamebegin() {
        String terningslag =  "Terningeslag 1: " + terning [0] + "   Terningeslag 2: " + terning[1] + "   Terningslag 3: " + terning[2] + "   Terningeslag 4: " + terning[3] + "   Terningeslag 5: " + terning[4];
        text (terningslag, 0, 20); // Displayer teksten som en string
    }
    // starter når man trykker på en knap
    public void keyPressed() {
        clear(); //fjerner alt på data der er skrevet/tegnet i void
        gamebegin(); // kører void gamebegin
        for(int i=0;i<result.length ; i++){
            result[i]=0;
        } // Resetter værdierne for resultaterne for at undgå tidligere slag også er med i dataen
        resultcheck(); // Tjekker resultat fra kast
        String resultat = "1: " + result[0] + ".stk " + "              2: " + result[1] + ".stk " + "                 3: " + result[2] + ".stk " + "         4: " + result[3] + ".stk " + "          5: " + result[4] + ".stk " + "       6: " + result[5] + ".stk ";
        text(resultat, 0, 50); // printer resultat ud
        Displaytoens (); //Printer output ud af hvad for nogle terninger der er 2 ens.
        Displaytreens (); //Printer output ud af hvad for nogle terninger der er 3 ens.
        Displayfireens (); //Printer output ud af hvad for nogle terninger der er 4 ens.
        Displayfemens(); ////Printer output ud af hvad for nogle terninger der er 5 ens.
        fuldhus (); // printer output hvis der er displaytoens og displaytreens i samme kast
    }

    //Tjekker hvad der er blevet slået
    public void resultcheck() {
        for (int i = 0; i < terning.length; i++) { // hvis nogen af terningerne slår 1,2,3 osv bliver der lagt en til af det der er blevet slået.
            if (terning [i] == 1) { // lægger 1 til hver gang et er slået.
                result[0] += terning [i];
            } else if (terning [i] == 2) { // lægger 1 til hver gang 2 er slået osv.
                result[1] += +1;
            } else if (terning [i] == 3) {
                result[2] += +1;
            } else if (terning [i] == 4) {
                result[3] += +1;
            } else if (terning [i] == 5) {
                result[4] += +1;
            } else if (terning [i] == 6) {
                result[5]+= +1;
            }
        }
    }

    //Printer output af hvad for nogle terninger der er 2 ens af
    public int Displaytoens() {
        int xoffset = 0;
        int value = 0;
        for (int i = 0; i < terning.length; i++) { //kører loopet 5 gange  da terning.le
            if (result [i] == 2) {
                value = i+1; // Giver +1 til resultat da det er 0-5 uden nu læses det som 1-6
                text("Topar:" +value,xoffset,IntMelin*2); // interval mellem linjer= InMelin
                xoffset= xoffset + 100;
            } else  {
                text("Topar:",0,IntMelin*2);
            }
        }
        return value; // returner værdien
    }

    //Printer output af hvad for nogle terninger der er tre ens af
    public int Displaytreens() {
        int value =0;
        for (int i = 0; i < terning.length; i++) {
            if (result [i] == 3) {
                value = i+1;
                text("Trepar:" +value,0 ,IntMelin*3);
            } else {
                text("Trepar:",0,IntMelin*3); }
        }
        return value;
    }

    //Printer output af hvad for nogle terninger der er fire ens af
    public int Displayfireens() {
        int value =0;
        for (int i = 0; i < terning.length; i++) {
            if (result [i] == 4) {
                value = i+1;
                text("Fireens:" +value,0 ,IntMelin*4);
            } else {
                text("Fireens:",0,IntMelin*4);}
        }
        return value;
    }

    //Printer output af hvad for nogle terninger der er fem ens af
    public int Displayfemens() {
        int value =0;
        for (int i = 0; i < terning.length; i++) {
            if (result [i] == 5) {
                value = i+1;
                text("Femens:" +value,0 ,IntMelin*5);
            } else {
                text("Femens:",0,IntMelin*5);
                value = i+1;
            }}
        return value;
    }
    //tjekker for fuldt hus
    public boolean fuldhus() {
        int value= 0; // angiver 2 værdier
        int value2= 0;

        for(int i=0; i< result.length ;i++){
            if(result [i] == 2) value = i+1; // angiver at værdierne skal være 2 og 3
            if(result [i] == 3) value2= i+1;
        }
        boolean isHouse = value!=0 && value2 != 0; // Hvis værdierne ikke er 0

        if(isHouse){ // hvis værdierne ikke er 0 kører texten nedeunder
            text("Du har fuldt hus"+ " med  2 " + value + "'ere  og 3 " + value2+"'ere",0,IntMelin*6); // printer tekst
        } else { // Kører hvis det ovenover ikke kører
            text("Du har intet hus",0,IntMelin*6);
        } return isHouse;
    }
    public void settings() {  size (1000, 1000); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "terning.terning" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
