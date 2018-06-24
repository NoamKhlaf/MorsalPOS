package sd.code.morsalpos.common;

/**
 * Created by alaa on 5/5/2018.
 */

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * Class which provides methods for encrypting and decrypting strings using a
 * DES encryption algorithm. Strings can be encrypted and then are returned
 * translated into a Base64 Ascii String.
 *
 * @author Tim Archer 11/11/03
 * @version $Revision: 1.2 $
 */
public class Crypto {

    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    /**
     * Construct a new object which can be utilized to encrypt and decrypt
     * strings using the specified key with a DES encryption algorithm.
     *
     * @param key The secret key used in the crypto operations.
     * @throws Exception If an error occurs.
     *
     */
    public Crypto(SecretKey key) throws Exception {

        System.out.println(key.toString());
        encryptCipher = Cipher.getInstance("DES");
        decryptCipher = Cipher.getInstance("DES");
        //final IvParameterSpec iv = new IvParameterSpec(new byte[24]);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);


    }

    /**
     * Encrypt a string using DES encryption, and return the encrypted string as
     * a base64 encoded string.
     *
     * @param unencryptedString The string to encrypt.
     * @return String The DES encrypted and base 64 encoded string.
     * @throws Exception If an error occurs.
     */
    public String encryptBase64(String unencryptedString) throws Exception {
        // Encode the string into bytes using utf-8
        byte[] unencryptedByteArray = unencryptedString.getBytes("UTF8");

        // Encrypt
        byte[] encryptedBytes = encryptCipher.doFinal(unencryptedByteArray);

        // Encode bytes to base64 to get a string
        byte[] encodedBytes = Base64.encodeBase64(encryptedBytes);

        return new String(encodedBytes);
    }

    /**
     * Decrypt a base64 encoded, DES encrypted string and return the unencrypted
     * string.
     *
     * @param encryptedString The base64 encoded string to Crypto.
     * @return String The decrypted string.
     * @throws Exception If an error occurs.
     */
    public String decryptBase64(byte[] encryptedString) throws Exception {
        // Encode bytes to base64 to get a string

        byte[] decodedBytes = Base64.decodeBase64(encryptedString);

        // Decrypt
        byte[] unencryptedByteArray = decryptCipher.doFinal(decodedBytes);

        // Decode using utf-8
        return new String(unencryptedByteArray, "UTF8");
    }

    /**
     * Main unit test method.
     *
     * @param args Command line arguments.
     *
     */
    public static void main(String args[]) {
        try {
            //Generate the secret key

            byte[] TMK = new byte[8];
            byte[] byteKey;
            //StringBuilder p1 = new StringBuilder(16);

            TMK = Hex.decodeHex(Config.TMK.toCharArray());
            byteKey = Hex.decodeHex("d5e4612fe5e49297".toCharArray());

            DESKeySpec key = new DESKeySpec(TMK);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            //Instantiate the encrypter/decrypter
            Crypto crypt = new Crypto(keyFactory.generateSecret(key));
            //String unencryptedString = "2134";

            //String encryptedString = crypt.encryptBase64(unencryptedString);
            //System.out.println("Encrypted String:"+encryptedString);
            //Decrypt the string
            String unencryptedString = crypt.decryptBase64(byteKey);
            // UnEncrypted String:Message
            System.out.println("UnEncrypted String:" + unencryptedString);

        } catch (Exception e) {
            System.err.println("Error:" + e.toString());
        }
    }

    public static byte[] hexStrToByteArray(String hex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(hex.length() / 2);

        for (int i = 0; i < hex.length(); i += 2) {
            String output = hex.substring(i, i + 2);
            int decimal = Integer.parseInt(output, 16);
            baos.write(decimal);
        }
        return baos.toByteArray();
    }
}
