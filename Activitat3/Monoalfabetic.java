package Activitat3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
public class Monoalfabetic{
    private static final char[] alfabetoMin = ("abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü").toCharArray();
    private static Map<Character, Character> m = new HashMap<>();
    private static final String[] casos = {"Hola22em33dic44Ignacio55", "SCPF es una empresa de publicidad?!?!", "@Nueva era del Xokas__"};
    public static void main(String[] args){
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
        System.out.println("Muestreo de shuffle en las letras");
        System.out.println("---------------------------------");
        System.out.println(m);
        System.out.println("Muestras de prueba");
        System.out.println("------------------");
        for(String caso: casos){
            String xifrat = xifraMonoAlfa(caso);
            System.out.println(xifrat);
            String descifrat = desxifraMonoAlfa(xifrat);
            System.out.println(descifrat);
            System.out.println();
        }
    }
    private static ArrayList<Character> permutaAlfabet(ArrayList<Character> shuffled){
        Collections.shuffle(shuffled);
        return shuffled;
    }
    private static void convertToArrayList(ArrayList<Character> alfabeto){
        for (char a : alfabetoMin) {
            alfabeto.add(a);
        }
    }
    public static String xifraMonoAlfa(String mensaje){
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
    private static Character buscaLlavePorValor(Character buscado){
        for(Map.Entry<Character,Character> set: m.entrySet()){
            if(set.getValue().equals(buscado)){
                return set.getKey();
            }
        }
        return null;
    }
    public static String desxifraMonoAlfa(String xifrat){
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