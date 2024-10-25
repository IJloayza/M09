package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int BYTES = 16;
    private byte[] iv = new byte[BYTES];
    //private static final String CLAU = "LaClauSecretaQueVulguis";
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            byte[] cifrat = xifraAES(msg, clau);
            TextXifrat text = new TextXifrat(cifrat);
            return text;
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        try {
            byte[] cifrado = xifrat.getBytes();
            String descifrado = desxifraAES(cifrado, clau);
            return descifrado;
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }


    public byte[] xifraAES(String msg, String password)throws Exception{
            SecureRandom secureRandom = new SecureRandom();
            //Obtenir els bytes de l’String
            byte[] bytesString = msg.getBytes();
            // Genera IvParameterSpec
            secureRandom.nextBytes(iv);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            // Genera hash
            byte[] clau = password.getBytes("UTF-8");
            byte[] correcteClau = Arrays.copyOf(clau, BYTES);
            MessageDigest m = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] key = m.digest(correcteClau);
            SecretKeySpec k = new SecretKeySpec(key, ALGORISME_XIFRAT);
            // Encrypt.
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
			cipher.init(Cipher.ENCRYPT_MODE, k, ivspec);
            byte[] cifrado = cipher.doFinal(bytesString);
            // Combinar IV i part xifrada.
            byte[] ivbyte = new byte[iv.length + cifrado.length];
            System.arraycopy(iv, 0, ivbyte, 0, iv.length);
            System.arraycopy(cifrado, 0, ivbyte, iv.length, cifrado.length);
            // return iv+msgxifrat
            return ivbyte;
            
    }
    public String desxifraAES(byte[] bMsgXifrat, String password)throws Exception{
        // Extreure l'IV.
        byte[] ivPart = new byte[BYTES];
        System.arraycopy(bMsgXifrat, 0, ivPart, 0, BYTES);
        IvParameterSpec ivspec = new IvParameterSpec(ivPart);
        // Extreure la part xifrada.
        byte[] cifrat = new byte[bMsgXifrat.length - 16];
        System.arraycopy(bMsgXifrat, BYTES, cifrat, 0, cifrat.length);
        // Fer hash de la clau
        byte[] clau = password.getBytes("UTF-8");
        byte[] correcteClau = Arrays.copyOf(clau, BYTES);
        MessageDigest m = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = m.digest(correcteClau);
        SecretKeySpec k = new SecretKeySpec(key, ALGORISME_XIFRAT);
        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, k, ivspec);
        byte[] descifrat = cipher.doFinal(cifrat);
        // return String desxifrat
        return new String(descifrat, "UTF-8");
    }
}
