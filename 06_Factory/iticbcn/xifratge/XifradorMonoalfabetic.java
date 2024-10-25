package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
public class XifradorMonoalfabetic implements Xifrador{
    private final char[] alfabetoMin = ("abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü").toCharArray();
    private Map<Character, Character> m = new HashMap<>();
    
    public XifradorMonoalfabetic(){
        ArrayList<Character> alfabeto =new ArrayList<Character>();
        convertToArrayList(alfabeto);
        ArrayList<Character> shuffled = new ArrayList<>();
        for(Character c: alfabeto){
            shuffled.add(c);
        }
        permutaAlfabet(shuffled);
        for(int i = 0; i < alfabeto.size(); i++){
            m.put(alfabeto.get(i), shuffled.get(i));
        }
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String cifrado = xifraMonoAlfa(msg);
        TextXifrat textCifrado = new TextXifrat(cifrado);
        return textCifrado;
    }
    public String desxifra(TextXifrat xifrat, String clau)throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String cifrat = xifrat.toString();
        String descifrado = desxifraMonoAlfa(cifrat);
        return descifrado;
    }

    private ArrayList<Character> permutaAlfabet(ArrayList<Character> shuffled){
        Collections.shuffle(shuffled);
        return shuffled;
    }
    private void convertToArrayList(ArrayList<Character> alfabeto){
        for (char a : alfabetoMin) {
            alfabeto.add(a);
        }
    }
    public String xifraMonoAlfa(String mensaje){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < mensaje.length();i++){
            Character letra = mensaje.charAt(i);
            if(Character.isLetter(letra)){
                if(Character.isUpperCase(letra)){
                    Character lower = Character.toLowerCase(letra); 
                    s.append(Character.toUpperCase(m.get(lower)));
                } else{
                    s.append(m.get(letra));
                }
            }else{
                s.append(letra);
            }
        }
        return s.toString();
    }
    private Character buscaLlavePorValor(Character buscado){
        for(Map.Entry<Character,Character> set: m.entrySet()){
            if(set.getValue().equals(buscado)){
                return set.getKey();
            }
        }
        return null;
    }
    public String desxifraMonoAlfa(String xifrat){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < xifrat.length(); i++){
            Character letra = xifrat.charAt(i);
            if(Character.isLetter(letra)){
                if(Character.isUpperCase(letra)){
                    s.append(Character.toUpperCase((buscaLlavePorValor(Character.toLowerCase(letra)))));
                } else{
                    s.append(buscaLlavePorValor(letra));
                }
            }else{
                s.append(letra);
            }
        }
        return s.toString();
    }
}