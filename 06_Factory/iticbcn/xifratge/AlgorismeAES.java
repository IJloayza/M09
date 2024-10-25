package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        Xifrador x = new XifradorAES();
        return x;
    }
}
