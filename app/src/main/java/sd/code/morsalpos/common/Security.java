package sd.code.morsalpos.common;

/**
 * Created by alaa on 12/29/2017.
 */

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.JSONObject;

/**
 * @author code
 */
public class Security {

    public static String getAuthorization(String publicKey, String pin, String uuid) {

        String authorization = null;
        try {
            authorization = Security.encrypt(uuid + pin, publicKey);
        } catch (Exception e) {

        }

        return authorization;

    }

    public static String encrypt(String text, String pubKeyStr) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        String cipherText = null;
        //     byte[] keyByte = Base64.decode(pubKeyStr);
        //byte[] bytearray = DatatypeConverter.parseBase64Binary(pubKeyStr);

        byte[] bytearray = Base64.decode(pubKeyStr.getBytes(), Base64.DEFAULT);

        X509EncodedKeySpec s = new X509EncodedKeySpec(bytearray);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
        }

        Key pubKey = null;

        if (factory != null) {
            try {
                pubKey = factory.generatePublic(s);

            } catch (InvalidKeySpecException e) {

            }
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            //cipherText=(new String(Base64.encode(cipher.doFinal(text.getBytes()))));

        } catch (NoSuchAlgorithmException e) {
        }
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());

        String encryptedInString = new String(Base64Coder.encode(encryptedBytes));


        return encryptedInString;
        //return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    public static String encrypt2(String text, String pubKeyStr) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        String cipherText = null;
        //     byte[] keyByte = Base64.decode(pubKeyStr);
        //byte[] bytearray = DatatypeConverter.parseBase64Binary(pubKeyStr);
        byte[] bytearray = Base64.decode(pubKeyStr, Base64.DEFAULT);
        X509EncodedKeySpec s = new X509EncodedKeySpec(bytearray);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
        }

        Key pubKey = null;

        if (factory != null) {
            try {
                pubKey = factory.generatePublic(s);

            } catch (InvalidKeySpecException e) {

            }
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        } catch (NoSuchAlgorithmException e) {
        }
        return Base64.encodeToString(cipher.doFinal(text.getBytes()), Base64.DEFAULT);
    }

    public static String getUUID() {

        return UUID.randomUUID().toString();

    }

    public static JSONObject getBasicParameters() {
        JSONObject obj = new JSONObject();
        String uuid = UUID.randomUUID().toString();
        try {
            obj.put("UUID", uuid);
            Date now = new Date();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            obj.put("tranDateTime", format.format(now));
        } catch (Exception e) {
        }

        return obj;
    }

    public static String getCurrentTranDateTime() {

        Date now = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        return format.format(now);
    }

    private static int getRandomNumber(int min, int max) {

        Random r = new Random();
        int res = r.nextInt(max - min + 1) + min;
        return res;
    }

    public static int getSystemTraceAuditNumber() {
        return getRandomNumber(1, 999999);
    }

    public static void main(String[] args) {
        System.out.println(getSystemTraceAuditNumber());
    }

}
