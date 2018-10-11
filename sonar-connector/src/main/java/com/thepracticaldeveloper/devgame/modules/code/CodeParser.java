package com.thepracticaldeveloper.devgame.modules.code;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CodeParser {

    private final String pub1 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5jO1hXLNTaYc55OQ4T3";
    private final String pub2 = "5i7YyJ7jCydqkNEhWZptdby30xh/6QqQFN8L/Ly0hr/bIggmOTrBzAB0rr26PA6IB5NAZndWAKUX7jH/snh3";
    private final String pub8 = "jVs88FEV7O97Kd0FboRRNga8Rn2M8oZBSXVBTdd83r6dlh14uKj5bIMyeCwBcA4Efe05yB/AaHC9Ag7lg1D5j4K/v5QRQMP2V";
    private final String pub9 = "zU2AAHHHWsPnlflM/2XdwHgYB90UzstE2yoxhPZNXVv2n60KcFGooKszvYddZoFsw9T/gByXufJO3kBtk1Hd3liWdO";
    private final String pub12 = "XL6/IDTBH94ds4Nw30hmvrYFkd1pNX2AMsHoTjGICh4N/uzl1iiQIDAQAB";

    private PublicKey getPublicKey() {
        try {
            final byte[] pubKeyBytes = Base64.getDecoder().decode(pub1 + pub2 + pub8 + pub9 + pub12);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (final Exception e) {
            throw new RuntimeException("Error loading public key", e);
        }
    }

    public CodeDetails getCodeDetails(final String encryptedCodeBase64) throws InvalidCodeException {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, getPublicKey());
            final String decryptedCode = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedCodeBase64)));
            return CodeDetails.fromCodeChain(decryptedCode);
        } catch (final Exception e) {
            throw new InvalidCodeException("Error while trying to read the code", e);
        }
    }
}
