package Activitat2;

import java.util.Random;

public class RotX {
    private final String alfabeto = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü";
    private final char[] alfabetoMin = alfabeto.toCharArray();
    private final char[] alfabetoMax = alfabeto.toUpperCase().toCharArray();
    private static final String[] casos = {"Hola22em33dic44Ignacio55", "SCPF es una empresa de publicidad?!?!", "@Nueva era del Xokas__"};

public static void main(String[] args) throws Exception{
    RotX xifra = new RotX();
    Random random = new Random();
    for(int i = 0; i < casos.length; i++){
        int aleatorio = random.nextInt(0, 38);
        System.out.println("--------------------");
        System.out.println("numero aleatorio:  " + aleatorio);
        System.out.println("--------------------");
        String cifrado = xifra.xifraRotX(casos[i], aleatorio);
        System.out.println("--------------------");
        System.out.println("      XIFRA ROT     ");
        System.out.println("--------------------");
        System.out.println(cifrado + "  <- xifrat by ROTX");
        String descifrado = xifra.desxifraRotX(cifrado, aleatorio);
        System.out.println(descifrado + "  <- descifrat by ROTX");
        System.out.println("--------------------");
        System.out.println("     FORÇA BRUTA    ");
        System.out.println("--------------------");
        xifra.forcaBrutaRotX(cifrado);
    }
}

public String xifraRotX(String mensaje, int desplaza){
    StringBuilder nuevoMensaje = new StringBuilder();
    for(int i = 0; i < mensaje.length(); i++){
        char letra = mensaje.charAt(i);
        if(Character.isLetter(letra)){
            if(Character.isUpperCase(letra)){
                for(int j = 0 ; j < alfabetoMax.length; j++){
                    if(alfabetoMax[j] == letra){
                        nuevoMensaje.append(alfabetoMax[((j + desplaza) % alfabetoMax.length)]);
                        break;
                    }
                }
            }else{
                for(int l = 0 ; l < alfabetoMin.length; l++){
                    if(alfabetoMin[l] == letra){
                        nuevoMensaje.append(alfabetoMin[((l + desplaza) % alfabetoMin.length)]);
                        break;
                    }
                }
            }
        } else{
            nuevoMensaje.append(letra);
        }
    }
    return nuevoMensaje.toString();
}

public String desxifraRotX(String mensaje, int desplaza){
    StringBuilder nuevoMensaje = new StringBuilder();
    for(int i = 0; i < mensaje.length(); i++){
        char letra = mensaje.charAt(i);
        if(Character.isLetter(letra)){
            nuevoMensaje.append(desxifraLetra(letra, desplaza));
        } else{
            nuevoMensaje.append(letra);
        }
    }
    return nuevoMensaje.toString();
}

private String desxifraLetra(char letra, int desplaza){
    String nuevoMensaje = "";
    if(Character.isUpperCase(letra)){
        for(int j = 0 ; j < alfabetoMax.length; j++){
            if(alfabetoMax[j] == letra){
                int result = (j - desplaza) %alfabetoMax.length;
                if(result < 0){
                    nuevoMensaje += (alfabetoMax[alfabetoMax.length + result]);
                } else{
                    nuevoMensaje += alfabetoMax[result];
                }
            }
        }
    }else{
        for(int j = 0 ; j < alfabetoMin.length; j++){
            if(alfabetoMin[j] == letra){
                int result = (j - desplaza) %alfabetoMin.length;
                if(result < 0){
                    nuevoMensaje += (alfabetoMin[alfabetoMin.length + result]);
                } else{
                    nuevoMensaje += alfabetoMin[result];
                }
            }
        }
    }
    return nuevoMensaje;
}

public void forcaBrutaRotX(String cifrat){
    for(int i = 1; i <= alfabeto.length(); i++){
        System.out.println(desxifraRotX(cifrat, i) + "  <-"+ i);
    }
}

}

