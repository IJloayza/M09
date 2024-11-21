import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.Duration;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Hashes {
    private final char[] CHARSET = ("abcdefABCDEF1234567890!").toCharArray(); 
    private int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt){
        String hashSha = null;
        try {
            byte[] salto = salt.getBytes();
    
            MessageDigest sha = MessageDigest.getInstance("SHA-512");
            sha.update(salto);
            byte[] hash = sha.digest(pw.getBytes());
    
            HexFormat hex = HexFormat.of();
            hashSha = hex.formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return hashSha;
    }
    
    public String getPBKDF2AmbSalt(String pw, String salt) {
        String hash = null;
        try {
            byte[] abSalt = salt.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), abSalt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] abHash = factory.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            hash = hex.formatHex(abHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e){
            e.printStackTrace();
        }
        return hash;
    }
    public String forcaBruta(String alg, String hash, String salt)throws Exception{
        char[] pass = new char[6];
        npass = 0;
        String password = new String(pass);
        String result = null;
        for (int i = 0; i < CHARSET.length; i++) {
            pass[0] = CHARSET[i];
            password = new String(pass);
            result = selectAlg(alg, password, salt);
            if(result.equals(hash))return password;
            for (int j = 0; j < CHARSET.length; j++) {
                pass[1] = CHARSET[j];
                password = new String(pass);
                result = selectAlg(alg, password, salt);
                if(result.equals(hash))return password;
                for (int j2 = 0; j2 < CHARSET.length; j2++) {
                    pass[2] = CHARSET[j2];
                    password = new String(pass);
                    result = selectAlg(alg, password, salt);
                    if(result.equals(hash))return password;
                    for (int k = 0; k < CHARSET.length; k++) {
                        pass[3] = CHARSET[k];
                        password = new String(pass);
                        result = selectAlg(alg, password, salt);
                        if(result.equals(hash))return password;
                        for (int k2 = 0; k2 < CHARSET.length; k2++) {
                            pass[4] = CHARSET[k2];   
                            password = new String(pass);
                            result = selectAlg(alg, password, salt);
                            if(result.equals(hash))return password;
                            for (int l = 0; l < CHARSET.length; l++) {
                                pass[5] = CHARSET[l];
                                password = new String(pass);
                                result = selectAlg(alg, password, salt);
                                if(result.equals(hash))return password;
                            }
                        }
                        
                    }
                    
                }
            }
        }
        

        return password;
    }

    private String selectAlg(String alg, String pw, String salt)throws Exception{
        String resultHash = null;
        npass++;
        switch (alg) {
            case "SHA-512":
                resultHash = getSHA512AmbSalt(pw, salt);
                break;
            case "PBKDF2":
                resultHash = getPBKDF2AmbSalt(pw, salt);
                break;
        
            default:
                break;
        }
        return resultHash;
    }

    public String getInterval(long t1, long t2){
        long result = t2 - t1;
        Duration time = Duration.ofMillis(result);

        long dias = time.toDays();
        long horas = time.toHoursPart();
        long minutos = time.toMinutesPart();
        long segundos = time.toSecondsPart();
        long milliseconds = time.toMillisPart();

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dias, horas, minutos, segundos, milliseconds);
    }
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
        System.out.printf("===========================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("---------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");
        
        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("---------------------------\n\n");
        }
    }
}
