package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() {
        Xifrador x = new XifradorPolialfabetic();
        return x;
    }
}
