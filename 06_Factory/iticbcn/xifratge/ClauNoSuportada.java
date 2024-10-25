package iticbcn.xifratge;

public class ClauNoSuportada extends Exception{
    public ClauNoSuportada() {
       super("amen");
    }
    public ClauNoSuportada(String missatge) {
        super(missatge);
    }
}
