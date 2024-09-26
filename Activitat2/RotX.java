package Activitat2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RotX {
    private final String alfabeto = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü";
    private final char[] alfabetoMin = alfabeto.toCharArray();
    private final char[] alfabetoMax = alfabeto.toUpperCase().toCharArray();
    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

public static void main(String[] args) throws Exception{
    for(int i = 0; i < 5; i++){
    System.out.println("Introduce un texto(press enter para salir)");
    String mensaje = entrada.readLine();
    if(mensaje.isBlank()){
        break;
    }
    RotX xifra = new RotX();
    mensaje = xifra.xifraRotX(mensaje, 13);
    System.out.println(mensaje + "   <- xifrat by ROTX");
    mensaje = xifra.desxifraRotX(mensaje, 13);
    System.out.println(mensaje + "   <- descifrat by ROTX");
    xifra.forcaBrutaRotX("Éqíà yn?nzn!!");
    }
}

public String xifraRotX(String mensaje, int desplaza){
    StringBuilder nuevoMensaje = new StringBuilder(mensaje.length());
    for(int i = 0; i < mensaje.length(); i++){
        char letra = mensaje.charAt(i);
        if(Character.isLetter(letra)){
            nuevoMensaje.append(cifraLetra(letra, desplaza));
        } else{
            nuevoMensaje.append(letra);
        }
    }
    return nuevoMensaje.toString();
}

public String desxifraRotX(String mensaje, int desplaza){
    StringBuilder nuevoMensaje = new StringBuilder(mensaje.length());
    for(int i = 0; i < mensaje.length(); i++){
        char letra = mensaje.charAt(i);
        if(Character.isLetter(letra)){
            nuevoMensaje.append(desxifraLetra(letra, 13));
        } else{
            nuevoMensaje.append(letra);
        }
    }
    return nuevoMensaje.toString();
}

private String cifraLetra(char letra, int desplaza){
    String nuevoMensaje = "";
    if(Character.isUpperCase(letra)){
        for(int j = 0 ; j < alfabetoMax.length; j++){
            if(alfabetoMax[j] == letra){
                nuevoMensaje += alfabetoMax[(j + desplaza)%alfabetoMax.length];
            }
        }
    }else{
        for(int j = 0 ; j < alfabetoMin.length; j++){
            if(alfabetoMin[j] == letra){
                nuevoMensaje += alfabetoMin[(j + desplaza)%alfabetoMin.length];
            }
        }
    }
    return nuevoMensaje;
}

private String desxifraLetra(char letra, int desplaza){
    String nuevoMensaje = "";
    if(Character.isUpperCase(letra)){
        for(int j = 0 ; j < alfabetoMax.length; j++){
            if(alfabetoMax[j] == letra){
                int result = j - desplaza;
                if(result < 0){
                    nuevoMensaje += alfabetoMax[alfabetoMax.length + result];
                } else{
                    nuevoMensaje += alfabetoMax[result];
                }
            }
        }
    }else{
        for(int j = 0 ; j < alfabetoMin.length; j++){
            if(alfabetoMin[j] == letra){
                int result = j -desplaza;
                if(result < 0){
                    nuevoMensaje += alfabetoMin[alfabetoMin.length + result];
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
        System.out.println(desxifraRotX(cifrat, i));
    }
}

}
