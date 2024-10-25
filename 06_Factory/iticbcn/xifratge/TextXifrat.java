package iticbcn.xifratge;

public class TextXifrat {
    private byte[] cifrat;
    public TextXifrat(byte[] msg){
        this.cifrat = msg;
    }
    public TextXifrat(String msg){
        this.cifrat = msg.getBytes();
    }
    @Override
    public String toString(){
        return new String(cifrat);
    }

    public byte[] getBytes(){
        return cifrat;
    }
}
