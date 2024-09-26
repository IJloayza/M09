package Activitat1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rot13{
private final String alfabeto = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z,á,à,é,è,í,ì,ï,ó,ò,ú,ù,ü";
private final String[] alfabetoMin = alfabeto.split(",");
private final String[] alfabetoMax = alfabeto.toUpperCase().split(",");
private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

public static void main(String[] args) throws Exception{
    for(int i = 0; i < 5; i++){
    System.out.println("Introduce un texto(press enter para salir)");
    String mensaje = entrada.readLine();
    if(mensaje.isBlank()){
        break;
    }
    Rot13 xifra = new Rot13();
    mensaje = xifra.xifraRot13(mensaje);
    System.out.println(mensaje + "   <- xifrat by ROT13");
    mensaje = xifra.descifraRot13(mensaje);
    System.out.println(mensaje + "   <- descifrat by ROT13");
    }
}

public String xifraRot13(String mensaje){
    StringBuilder nuevoMensaje = new StringBuilder(mensaje.length());
    for(int i = 0; i < mensaje.length(); i++){
        char letra = mensaje.charAt(i);
        if(Character.isLetter(letra)){
            nuevoMensaje.append(cifraLetra(letra));
        } else{
            nuevoMensaje.append(letra);
        }
    }
    return nuevoMensaje.toString();
}

public String descifraRot13(String mensaje){
    StringBuilder nuevoMensaje = new StringBuilder(mensaje.length());
    for(int i = 0; i < mensaje.length(); i++){
        char letra = mensaje.charAt(i);
        if(Character.isLetter(letra)){
            nuevoMensaje.append(descifraLetra(letra));
        } else{
            nuevoMensaje.append(letra);
        }
    }
    return nuevoMensaje.toString();
}

private String cifraLetra(char letra){
    String nuevoMensaje = "";
    if(Character.isUpperCase(letra)){
        for(int j = 0 ; j < alfabetoMax.length; j++){
            if(alfabetoMax[j].equals(Character.toString(letra))){
                nuevoMensaje = alfabetoMax[(j + 13)%alfabetoMax.length];
            }
        }
    }else{
        for(int j = 0 ; j < alfabetoMin.length; j++){
            if(alfabetoMin[j].equals(Character.toString(letra))){
                nuevoMensaje = alfabetoMin[(j + 13)%alfabetoMin.length];
            }
        }
    }
    return nuevoMensaje;
}

private String descifraLetra(char letra){
    String nuevoMensaje = "";
    if(Character.isUpperCase(letra)){
        for(int j = 0 ; j < alfabetoMax.length; j++){
            if(alfabetoMax[j].equals(Character.toString(letra))){
                int result = j -13;
                if(result < 0){
                    nuevoMensaje = alfabetoMax[alfabetoMax.length + result];
                } else{
                    nuevoMensaje = alfabetoMax[result];
                }
            }
        }
    }else{
        for(int j = 0 ; j < alfabetoMin.length; j++){
            if(alfabetoMin[j].equals(Character.toString(letra))){
                int result = j -13;
                if(result < 0){
                    nuevoMensaje = alfabetoMin[alfabetoMin.length + result];
                } else{
                    nuevoMensaje = alfabetoMin[result];
                }
            }
        }
    }
    return nuevoMensaje;
}

}

