package Activitat4;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class Polialfabetic{
    private static final char[] alfabetoMin = ("abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü").toCharArray();
    private static final int clauSecreta = 19728749;
    private static Random r = new Random();
    public static void main(String[]args){
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila",
        "AAAAAAAAAAAAAAAAA",
        "AAAAAAAAAAAAAAAAA"};
        String msgsXifrats [] = new String[msgs.length];
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
        msgsXifrats [i] = xifraPoliAlfa (msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats [i]);
        }
        System.out.println("Desxifratge: \n-----------" );
        for (int i = 0; i < msgs.length; i++) {
        String msg = desxifraPoliAlfa (msgsXifrats [i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats [i], msg);
        }
    }
    private static String desxifraPoliAlfa(String xifrat) {
        initRandom(clauSecreta);
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < xifrat.length(); i++){
            char letra = xifrat.charAt(i);
            char[] shuffledAlf = permutaAlfabet(alfabetoMin);
            if(Character.isLetter(letra)){
                if(Character.isUpperCase(letra)){
                    for(int j = 0; j < alfabetoMin.length; j++){
                        if(Character.toLowerCase(letra) == shuffledAlf[j]){
                            s.append(Character.toUpperCase(alfabetoMin[j]));
                        }
                    }
                } else{
                    for(int j = 0; j < alfabetoMin.length; j++){
                        if(letra == shuffledAlf[j]){
                            s.append(alfabetoMin[j]);
                        }
                    }
                }
            } else{
                s.append(letra);
            }

        }
        return s.toString();  
    }
    private static String xifraPoliAlfa(String mensaje) {
        initRandom(clauSecreta );
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < mensaje.length(); i++){
            char letra = mensaje.charAt(i);
            char[] shuffledAlf = permutaAlfabet(alfabetoMin);
            if(Character.isLetter(letra)){
                if(Character.isUpperCase(letra)){
                    for(int j = 0; j < alfabetoMin.length; j++){
                        if(Character.toLowerCase(letra) == alfabetoMin[j]){
                            s.append(Character.toUpperCase(shuffledAlf[j]));
                        }
                    }
                }else{
                    for(int j = 0; j < alfabetoMin.length; j++){
                        if(letra == alfabetoMin[j]){
                            s.append(shuffledAlf[j]);
                        }
                    }
                }
            }else{
                s.append(letra);
            }   

        }
        return s.toString();  
    }
    private static Random initRandom(int clausecreta2) {
        r = new Random(clausecreta2);
        return r;
    }
    private static char[] permutaAlfabet(char[] alfabetoMin){
        ArrayList<Character> shuffled = convertToArrayList(alfabetoMin);
        Collections.shuffle(shuffled, r);
        char[] c = convertToArray(shuffled);
        return c;
    }
    private static ArrayList<Character> convertToArrayList(char[] alfabeto){
        ArrayList<Character> a = new ArrayList<>();
        for (char letra : alfabeto) {
            a.add(letra);
        }
        return a;
    }
    private static char[] convertToArray(ArrayList<Character> shuffled){
        char[] mezcla = new char[shuffled.size()];
        for (int i = 0; i < mezcla.length; i++) {
            mezcla[i] = shuffled.get(i);
        }
        return mezcla;
    }
}