package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory  {
    @Override
    public Xifrador creaXifrador() {
        Xifrador x = new XifradorRotX();
        return x;
    }
}
