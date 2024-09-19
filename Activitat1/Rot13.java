package Activitat1;

public class Rot13{
    private final String alfabeto = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z"; 
    private final String[] alfabetoMin = alfabeto.split(",");
    private final String[] alfabetoMax = alfabeto.toUpperCase().split(",");
    public String xifraRot13(String mensaje){
        String nuevoMensaje = "";
        for(int i = 0; i < mensaje.length(); i++){
            char letra = mensaje.charAt(i);
            if(Character.isLetter(letra)){
                boolean hoEs = Character.isUpperCase(i);
                if(hoEs){
                    for(int j = 0 ; j < alfabetoMax.length; j++){
                        if(alfabetoMax[j].equals(Character.toString(letra))){
                           nuevoMensaje = nuevoMensaje + alfabetoMax[(j + 13)%alfabetoMax.length]; 
                        }
                    }
                }else{
                    for(int j = 0 ; j < alfabetoMin.length; j++){
                        if(alfabetoMin[j].equals(Character.toString(letra))){
                           nuevoMensaje = nuevoMensaje + alfabetoMin[(j + 13)%alfabetoMin.length]; 
                        }
                    }
                }
            } else{
                nuevoMensaje = nuevoMensaje + letra; 
            }
        }
        return nuevoMensaje;
    }
    public String descifraRot13(String mensaje){
        String nuevoMensaje = "";
        for(int i = 0; i < mensaje.length(); i++){
            char letra = mensaje.charAt(i);
            if(Character.isLetter(i)){
                boolean hoEs = Character.isUpperCase(i);
                if(hoEs){
                    for(int j = 0 ; j < alfabetoMax.length; j++){
                        if(alfabetoMax[j].equals(Character.toString(letra))){
                           nuevoMensaje = nuevoMensaje + alfabetoMax[(j - 13)%alfabetoMax.length]; 
                        }
                    }
                }else{
                    for(int j = 0 ; j < alfabetoMin.length; j++){
                        if(alfabetoMin[j].equals(Character.toString(letra))){
                           nuevoMensaje = nuevoMensaje + alfabetoMin[(j - 13)%alfabetoMin.length]; 
                        }
                    }
                }
            }
        }
        return nuevoMensaje;
    }

}
