package Activitat2;

public class RotX {
    private final String alfabeto = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü";
    private final char[] alfabetoMin = alfabeto.toCharArray();
    private final char[] alfabetoMax = alfabeto.toUpperCase().toCharArray();
    private static final String[] casos = {"Hola e5435m di543c Ignacio234", "SCPF es una empresa de publicidad?!?!", "@Nueva era del Xokas"};

public static void main(String[] args) throws Exception{
    RotX xifra = new RotX();
    for(int i = 0; i < casos.length; i++){
    
    String cifrado = xifra.xifraRotX(casos[i], 17);
    System.out.println(cifrado + "  <- xifrat by ROTX");
    String descifrado = xifra.desxifraRotX(cifrado, 17);
    System.out.println(descifrado + "  <- descifrat by ROTX");
    xifra.forcaBrutaRotX(cifrado);
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
            nuevoMensaje.append(desxifraLetra(letra, desplaza));
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
    for(int i = 0; i <= alfabeto.length(); i++){
        System.out.println(desxifraRotX(cifrat, i) + "  <-"+ i);
    }
}

}

