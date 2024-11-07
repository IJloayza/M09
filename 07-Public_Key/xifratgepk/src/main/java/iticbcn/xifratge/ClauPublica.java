package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    private final String ALGORITMO_CIFRADO = "RSA";
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator keys = KeyPairGenerator.getInstance(ALGORITMO_CIFRADO);
        keys.initialize(2048);
        KeyPair k = keys.generateKeyPair();
        return k;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher cifrador = Cipher.getInstance(ALGORITMO_CIFRADO);
        cifrador.init(Cipher.ENCRYPT_MODE, clauPublica);

        byte[] textoCifrat = cifrador.doFinal(msg.getBytes());
        return textoCifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada)throws Exception{
        Cipher descifrador = Cipher.getInstance(ALGORITMO_CIFRADO);
        descifrador.init(Cipher.DECRYPT_MODE, ClauPrivada);

        byte[] descifrat = descifrador.doFinal(msgXifrat);
        String descifrado = new String(descifrat);
        return descifrado;
    } 
}
