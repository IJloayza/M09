package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory  {
    @Override
    public Xifrador creaXifrador() {
        Xifrador x = new XifradorMonoalfabetic();
        return x;
    }
}
