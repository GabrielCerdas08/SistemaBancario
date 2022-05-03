
package seguridad;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Encriptacion {
    private static String  ENCRYPT_KEY="PBKDF2WithHmacSHPBKDF2WithHmacSH";
   
    //Métodos para encriptar num de cuenta, contraseña y pin
   public String cifrarDato(String pDato) throws Exception{
        return encriptMethod(pDato); 
    }
     public String descifrarDato(String pDato) throws Exception{
        return encriptMethod(pDato); 
    }
     
    //Metodos privados de encriptacion
    private static String encriptMethod(String text) throws Exception {	
        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    private static String decryptMethod(String encrypted) throws Exception {
        byte[] encryptedBytes= Base64.getDecoder().decode(encrypted);

        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        String decrypted = new String(cipher.doFinal(encryptedBytes));

        return decrypted;
    }
   
    
}
