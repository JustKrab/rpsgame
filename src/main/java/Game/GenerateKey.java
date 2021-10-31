package Game;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


public class GenerateKey {


    private static final String HMAC = "HmacSHA256";


    public static byte[] generateSecureRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return bytes;

    }

    public static Mac hmac(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, KeyStoreException {
        Security.addProvider(new BouncyCastleProvider());
        KeyStore.getInstance("PKCS12","BC");
        Mac signer = Mac.getInstance(HMAC);
        SecretKeySpec keySpec = new SecretKeySpec(bytes, HMAC);
        signer.init(keySpec);
        return signer;
    }



    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }




}
