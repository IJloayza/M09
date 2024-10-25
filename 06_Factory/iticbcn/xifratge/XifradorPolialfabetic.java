package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class XifradorPolialfabetic implements Xifrador{
    private static final char[] alfabetoMin = ("abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü").toCharArray();
    private long clauSecreta;
    private Random r = new Random();

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            long clave = Long.parseLong(clau);
            this.clauSecreta = clave;
            String cifrado = xifraPoliAlfa(msg);
            TextXifrat textCifrado = new TextXifrat(cifrado);
            return textCifrado;
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }
    public String desxifra(TextXifrat xifrat, String clau)throws ClauNoSuportada{
        try {
            long clave = Long.parseLong(clau);
            this.clauSecreta = clave;
            String cifrat = xifrat.toString();
            String descifrado = desxifraPoliAlfa(cifrat);
            return descifrado;
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
    }


    public String desxifraPoliAlfa(String xifrat) {
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
    public String xifraPoliAlfa(String mensaje) {
        initRandom(clauSecreta);
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
    private Random initRandom(long clausecreta2) {
        r = new Random(clausecreta2);
        return r;
    }
    private char[] permutaAlfabet(char[] alfabetoMin){
        ArrayList<Character> shuffled = convertToArrayList(alfabetoMin);
        Collections.shuffle(shuffled, r);
        char[] c = convertToArray(shuffled);
        return c;
    }
    private ArrayList<Character> convertToArrayList(char[] alfabeto){
        ArrayList<Character> a = new ArrayList<>();
        for (char letra : alfabeto) {
            a.add(letra);
        }
        return a;
    }
    private char[] convertToArray(ArrayList<Character> shuffled){
        char[] mezcla = new char[shuffled.size()];
        for (int i = 0; i < mezcla.length; i++) {
            mezcla[i] = shuffled.get(i);
        }
        return mezcla;
    }
}