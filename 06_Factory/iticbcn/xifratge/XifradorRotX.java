package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{
    private final String alfabeto = "abcdefghijklmnñopqrstuvwxyzáàéèíìïóòúùü";
    private final char[] alfabetoMin = alfabeto.toCharArray();
    private final char[] alfabetoMax = alfabeto.toUpperCase().toCharArray();

    
    //S'ha de crear un constructor
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            int desplaza = Integer.parseInt(clau);
            if(desplaza < 0 || desplaza > 40){
                throw new NumberFormatException("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String cifrado = xifraRotX(msg, desplaza);
            TextXifrat textCifrado = new TextXifrat(cifrado);
            return textCifrado;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
    public String desxifra(TextXifrat xifrat, String clau)throws ClauNoSuportada{
        try {
            int desplaza = Integer.parseInt(clau);
            if(desplaza < 0 || desplaza > 40){
                throw new NumberFormatException("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            String cifrat = xifrat.toString();
            String descifrado = desxifraRotX(cifrat, desplaza);
            return descifrado;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
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

