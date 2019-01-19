/**
 * Programmname: Viereckbestimmung_HAIDEN
 * Aufnahemnr.:  20170023
 * Klasse / Katalognr.: 2BHIF / 6
 * Abgabe: 18.01.2019
 *
 * Beschreibung:
 * ======================================
 * Das Programm soll vier Punkte einlesen und dabei feststellen,
 * um welche Art von Viereck es sich handelt. Die Funktionen sollen
 * durch den beigelegten JUNIT Test getestet werden.
 */
package viereckbestimmung_haiden;

import java.util.Scanner; //Für Eingabe

/**
 * Diese Klasse enthaelt alle statischen Methoden zum Berechnen,... von
 * Vierecken
 *
 * @version 1.0
 * @author 20170023
 */
public class Viereckbestimmung {

    public static double eps = 1E-6; //Die Genauigkeit

    /**
     * Main-Methode welche die User-Interaktionen bereitstellt und die
     * Funktionen zum Einlesen und Ueberpruefen aufruft.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Punkt[] p = new Punkt[4];
        System.out.println("Willkommen! Bitte geben Sie vier Punkte ein: ");
        //Einlesen der Punkte
        for (int i = 0; i < p.length; i++) {
            System.out.print("Geben Sie den " + (i + 1) + ".Punkt ein [x/y] --> ");
            p[i] = liesPunkt(); //Aufrufen der liesPunkt Funktion und Einlesen des Punkte
        }
        /*
        Ueberpruefen um welche Form von Viereck es sich handelt
         */
        if (isViereck(p)) { //Falls es ein Viereck ist, wird herausgefunden welche Art
            if (isQuadrat(p)) { // Falls Quadrat => Ausgabe der Meldung
                System.out.println("Die eingegeben Punkte ergeben ein Quadrat!");
            } else if (isRaute(p)) { //Falls Raute => Ausgabe der Meldung 
                System.out.println("Die eingegeben Punkte ergeben eine Raute!");
            } else if (isRechteck(p)) { //Falls Rechteck => Ausgabe der Meldung
                System.out.println("Die eingegeben Punkte ergeben ein Rechteck!");
            } else if (isParallelogramm(p)) { //Falls Parallelogramm => Ausgabe der Meldung
                System.out.println("Die eingegeben Punkte ergeben ein Parallelogramm!");
            } else if (isDeltoid(p)) { //Falls Deltoid => Ausgabe der Meldung
                System.out.println("Die eingegeben Punkte ergeben einen Deltoid!");
            } else { //Wenn die eingegeben Punkte nichts anderes ergeben, ist es ein allg. Viereck
                System.out.println("Die eingegeben Punkte ergeben ein allgemeines Viereck!");
            }
        } else {
            System.out.println("Die eingegeben Punkte ergeben ein entartetes Viereck!");
        }

    }

    /**
     * Ueberprueft ob es ein Viereck ist
     * Ueberprueft, ob das Array NULL ist bzw. NULL an einzelnen Stellen aufweist.
     * Danach wird in einer For-Schleife ueberprueft, ob einzelne Punkte zusammenfallen
     * @param p Das Array der Punkte
     * @return true Wenn die Punkte ein Viereck ergeben
     * @return false Wenn die Punkte kein Viereck ergeben
     */
    static boolean isViereck(Punkt[] p) {
        if (isNull(p) == true) { //Ueberpruefen ob das Array bzw. Elemente des Arrays NULL sind
            return false; //Falls Ja, dann False zurueckgeben
        } else {
            /**
             * Ueberpruefen ob Punkte zusammenfallen => entartetes Viereck
             */
            for (int i = 0; i < p.length - 1; i++) {

                if (isPunktEqual(p[i], p[i + 1])) { //Aufrufen der Funktion um zu Ueberpruefen, ob die Punkte gleich sind
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Hilfs-Funktion um zu Ueberpruefen, ob es sich bei den Punkten 
     * um ein und den denselben handelt.
     * @param p 1.Punkt
     * @param q 2.Punkt
     * @return true Wenn sie gleich sind
     *         false Wenn sie nicht gleich sind
     */
    static boolean isPunktEqual(Punkt p, Punkt q) {
        return (p.x == q.x && p.y == q.y);
    }

    /**
     * Ueberprueft ob das uebergebene Array auf ein Quadrat.
     * Da eine Raute auch 4 gleich lange Seiten haben muss, wird die Funktion in Kombination
     * mit einem Diagonalenvergleich benutzt (Diagonalen muessen gleich lang sein).
     * @param p
     * @return true Wenn es ein Quadrat ist
     * @return false Wenn es kein Quadrat ist
     */
    static boolean isQuadrat(Punkt[] p) {
        if (!isViereck(p)) { //Ueberpruefen, ob es auch ein Viereck ist
            return false;
        } else {
            double diagAC = distanz(p[0], p[2]); //Berechnen der Diagonale AC
            double diagBD = distanz(p[1], p[3]); //Berechnen der Diagonale BD

            return isRaute(p) && isEqual(eps, diagAC, diagBD); //Zurueckgeben des Ergebnisses des Vergleiches

        }

    }

    /**
     * Ueberprueft das Array auf ein Rechteck
     * Zuerst werden die Diagonalen berechnet,
     * und da ein Rechteck ein besonders Paralellogramm ist wird dessen Funktion in Kombination mit einem 
     * Diagonalen Vergleich genutzt.
     * @param p Das Array von Punkten
     * @return true Wenn es ein Rechteck ist false Wenn es ein
     */
    static boolean isRechteck(Punkt[] p) {
        if (!isViereck(p)) {//Ueberpruefen, ob es auch ein Viereck ist
            return false;
        } else {
            //Diagonalen berechnen
            double diagAC = distanz(p[0], p[2]);
            double diagBD = distanz(p[1], p[3]);
            
            return (isParallelogramm(p) && isEqual(eps, diagBD, diagAC)); //Vergleichen und zurueckgeben des Ergebnisses

        }
    }

    /**
     * Ueberprueft das Punkte-Array auf ein Parallelogramm
     * Zuerst werden die Distanzen zwischen den Punkten berechnet und dann miteinander verglichen.
     * @param p Das Array von Punkten
     * @return true Wenn es ein Parallelogramm ist 
     * false Wenn es kein Parallelogramm ist
     */
    static boolean isParallelogramm(Punkt[] p) {
        if (!isViereck(p)) {//Ueberpruefen, ob es auch ein Viereck ist
            return false;
        } else {
            //Distanzen zwischen den Punkten berechnen
            double disAB = distanz(p[0], p[1]);
            double disBC = distanz(p[1], p[2]);
            double disCD = distanz(p[2], p[3]);
            double disDA = distanz(p[3], p[0]);

             //Zwei gegenueberliegende Seiten muessen gleich lang sein
            return (isEqual(eps, disAB, disCD) && isEqual(eps, disBC, disDA));
        }
    }

    /**
     * Ueberprueft ob das Array eine Raute ergibt.
     * Zuerst werden die Distanzen zwischen den Punkten berechnet und danach wird
     * ueberprueft, ob alle 4 Seiten gleich lang sind.
     * @param p Das Array von Punkten
     * @return true Wenn es eine Raute ist false Wenn es keine Raute ist
     */
    static boolean isRaute(Punkt[] p) {
        if (!isViereck(p)) {//Ueberpruefen, ob es auch ein Viereck ist
            return false;
        } else {
            //Berechnen der Distanzen
            double disAB = distanz(p[0], p[1]);
            double disBC = distanz(p[1], p[2]);
            double disCD = distanz(p[2], p[3]);
            double disDA = distanz(p[3], p[0]);

            //Vergleichen der Seiten (Alle muessen gleich lang sein)
            return isEqual(eps, disAB, disBC, disCD, disDA);
        }
    }

    /**
     * Ueberprueft ob das uebergebene Array von Punkten ein Deltoid ist
     * Zuerst werden die Distanzen zwischen den Punkten berechnet.
     * Nachdem wird verglichen: Mindestens zwei Seitenpaare muessen gleich sein
     * @param p Array der Punkte
     * @return true Wenn es ein Deltoid ist false Wenn es kein Deltoid ist
     */
    static boolean isDeltoid(Punkt[] p) {
        if (!isViereck(p)) {//Ueberpruefen, ob es auch ein Viereck ist
            return false;
        } else {
            //Berechnen der Distanzen zwischen den Punkten
            double disAB = distanz(p[0], p[1]);
            double disBC = distanz(p[1], p[2]);
            double disCD = distanz(p[2], p[3]);
            double disDA = distanz(p[3], p[0]);

            //Vergleichen (mindestens zwei Seitenpaare muessen gleich sein) und returnen des Ergebnisses
            return ((isEqual(eps, disAB, disBC) && isEqual(eps, disCD, disDA)) || (isEqual(eps, disBC, disCD) && isEqual(eps, disAB, disDA)));
        }
    }

    /**
     * Ueberprueft ob einer der Punkte NULL, also Nichts, ergibt
     *
     * @param p Das Array von Punkten
     * @return true wenn mindestens einer der Punkte fehlt bzw. das Array gesamt
     * eine NULL-Refernz aufweist 
     * 
     * false Wenn es kein NULL im Array gibt, 
     * bzw. das Array selber nicht NULL ist
     */
    static boolean isNull(Punkt[] p) {
        //Ueberpruefen ob das Array oder eines der Array-Plaetze NULL aufweist
        if (p == null || p[0] == null || p[1] == null || p[2] == null || p[3] == null) {
            return true;
        }
        return false;
    }
    /**
     * Diese Funktion liest einen Punkt ein returned eine neue Refernz zu diesem neuen Punkt
     * ein.
     * @return Punkt mit den eingegebenen Koordinaten
     */
    static Punkt liesPunkt() {
        double x = 0.0; //X-Koordinate
        double y = 0.0; //Y-Koordinate
        Scanner sc = new Scanner(System.in); //Scanner mit dem User-Input eingegeben wird
        boolean iftrue = true; //Variable fuer Schleife 
        do {

            String zeile = sc.nextLine().trim(); //Einlesen der User-Eingabe
            zeile = zeile.replace(".", ","); //Ersetzen der Punkte mit Beistrichen damit Einlesen funktioniert, falls der User . statt , eingibt
            String[] texte = zeile.split("/"); //Aufteilen der Zeile

            if (texte.length == 2) { //Ueberpruefen ob texte[] auch 2 Objekte hat
                Scanner zs = new Scanner(texte[0]); //Anlegen eines neuen Scanners der aus dem Text von texte liest
                //Ueberpruefen ob im Text ein Double vorkommt
                if (zs.hasNextDouble()) {
                    x = zs.nextDouble(); //Speichern des Doubles
                    zs = new Scanner(texte[1]); //Anlegen eines neuen Scanner Objekts welches auf den zweiten Text (y) zeigt)
                   //Ueberpruefen ob im Text ein Double vorkommt
                    if (zs.hasNextDouble()) {
                        y = zs.nextDouble(); //Einspeichern des Double-Wertes
                        iftrue = false; //Nun ist die Schleife beendet, da die Eingabe fertig ist
                    } else { //Falls kein Double gefunden wurde => Fehlermeldung
                        System.out.println("Fehlerhafte Eingabe! Sie muessen eine Zahl eingeben!");
                        System.out.print("Bitte nochmal wiederholen!\n");
                        iftrue = true;
                    }
                } else { //Falls kein Double gefunden wurde => Fehlermeldung
                    System.out.println("Fehlerhafte Eingabe! Sie muessen eine Zahl eingeben!");
                    System.out.print("Bitte nochmal wiederholen!\n");
                    iftrue = true;
                }

            } else { //Falls keine 2 Werte eingegeben wurde => Fehlermeldung
                System.out.println("Fehlerhafte Eingabe! Sie muessen 2 Werte eingeben");
                System.out.println("Bitte nochmal wiederholen!\n");
                iftrue = true;
            }
        } while (iftrue);
        return new Punkt(x, y); //Returnen des neuen Punktes mit den eingegeben Koordinaten
    }

    /**
     * Ueberprueft ob die Werte unter der Genauigkeit liegen.
     * Dafuer wird die Differenz aus den d1 und d2 berechnet und dabei ueberprueft,
     * ob es sich unter der Genauigkeit befindet.
     * @param eps Die Genaugikeit
     * @param d1 Der 1. zu ueberpruefende Wert
     * @param d2 Der 2. zu ueberpruefende Wert
     * @return true Wenn es sich unter eps befindet oder gleich ist
     *         false Wenn es ueber eps ist
     */
    static boolean isEqual(double eps, double d1, double d2) {
        return Math.abs(d1 - d2) <= eps; //Ueberpruefen und returnen des Ergebnisses des Vergleichs
    }

    /**
     * Ueberladene Version von isEqual welche eine Schnittstelle fuer mehrere Vergleichswerte bereitstellt.
     * Zuerst wird geschaut, ob sich mehr als 1 Wert in values befindet. Dann wird mit der normalen isEqual verglichen
     * und falls es zu einem falschen Ergebnis kommt, wird false zurueckgegeben.
     * @param eps Die Genauigkeit
     * @param values Das Array mit den Vergleichswerten
     * @return true Wenn die Werte unter oder gleich eps lagen
     *         false Wenn die Werte ueber eps lagen
     */
    static boolean isEqual(double eps, double... values) {
        if (values.length <= 1) { //Ueberpruefen ob sich mehr als 1 Wert in values befindet
            return false;
        }
        for (int i = 0; i < values.length - 1; i++) {
            if (!(isEqual(eps, values[i], values[i + 1]))) { //Vergleichen mit normaler isEqual-Methode
                return false; //Falls der Vergleich true ergibt, da die Werte dann ueber EPS liegen, wird false returned
            }
        }
        return true;
    }

    /**
     * Berechnet die Distanz zwischen zwei Punkten und retourniert sie.
     * Mit sqrt wird die Wurzel aus der Summe von den beiden potenzierten Differnzen von P und Q gezogen
     * @param p Punkt P
     * @param q Punkt Q
     * @return Die Distanz zwischen den beiden Punkten
     */
    static double distanz(Punkt p, Punkt q) {
        return Math.sqrt(Math.pow((p.x - q.x), 2) + Math.pow((p.y - q.y), 2)); //Berechnen und returnen des Ergebnisses
    }

}

/**
 * Diese Klasse beschreibt einen Punkt
 * @author 20170023
 */
class Punkt {

    double x;
    double y;

    public Punkt() {
        this.x = 0;
        this.y = 0;
    }

    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
