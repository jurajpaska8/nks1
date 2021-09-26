package fei.nks;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

public class Program
{
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException
    {
        // iv
        byte[] iv = {16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // key
        byte[] keyBytes = {58, 73, 13, 48, 0,0,0,0,0,0,0,0,0,0,0,0};
        SecretKeySpec secretKeySpec;

        // cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // cipher text in base64 encoding
        String testCt = "WEiKqbHjG5/D92DLxiTFjvc7HB1FKLuVt6FHnfvEjvl+TJ8F68MFmRrT04RubD6uluqjwhlE6k/ojp2ekIB7R+WILRh/1Gcqc5Mx4R6Uhdylp0EkEXOjRhwRibtAinnxfL1UZ3+rHS7RxbjD5X+0z1I4X2J60n/xKkyWsaFsRPxlXgNhcGz0D1kkqDVAbgqOqVS67u7KaOxatD42CFGCQKNQOkis6zp2wgnMOQvTw9PGY+bFN+5xfH1/+pbjaCSnewMRjCkow93/eSsFL4jX3VtsXKPtOs6ijZf/QISJjGjOwT5jpLoxmkcVqdKTNrPHLjcigIn9DPyk0pqH0pSmG1NcttebXxFw0CuiAFzJkdFQd+GQFOerbXNB6pypysjUlUFq0sbOT1buRkIT2C8YBg==";
        String exerci = "0fo16JtEgRad0kgi9N0X+SvgjGNTMdVb6odBTscGZ29RjPgdc6F8in08XsIhDUmuCoaeA23gN8gybPOMckmZZW4hNzPKyz5irvFiJIhyZ2v7Drxfn43R0WvRJSIKc1LFvq46WWc7HSmW3Kz/VYq1/63akbP4eiqYfd7yGGJdR3nSkecTkgNXAMCf+fJtrHUWV/Zjxo+i2CNvo29y6q14QbyCmk1ZKicIo7hPoBHiOUhD9Vci8/AP1Ln94WcIzjeKQ1sPYnRUuJL0/nkqG3yJYGEKY5qE6AGeQ5y3gkOQ9BT/KNXchYqa9QpNVcOnIOqS2bku3tIPGeh9OhBbrrwKtw==";
        byte[] ctByte = Base64.getDecoder().decode(exerci);
        Instant start = Instant.now();
        // TODO cca 2:20 for one 1*255*255*255 check
        for(int i0 = 0; i0 <= 255; i0++)
        {
            keyBytes[0] = (byte)i0;
            System.out.println(i0 + " time: " + Instant.now());
            for(int i1 = 0; i1 <= 255; i1++)
            {
                keyBytes[1] = (byte)i1;
                //System.out.println(".." + i1);
                for(int i2 = 0; i2 <= 255; i2++)
                {
                    keyBytes[2] = (byte)i2;
                    for(int i3 = 0; i3 <= 255; i3++)
                    {
                        try
                        {
                            keyBytes[3] = (byte)i3;
                            secretKeySpec = new SecretKeySpec(keyBytes, "AES");
                            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
                            byte[] openBytes = cipher.doFinal(ctByte);
                            String openText = new String(openBytes);
                            if(openText.contains(" the ") || openText.contains(" to ") || openText.contains(" and "))
                            {
                                Instant finded = Instant.now();
                                System.out.println("Time: " + finded + ", key: [" + i0 + ", " + i1 + ", " + i2 + ", " + i3 + "]");
                                System.out.println(openText);
                            }
                        }
                        catch (BadPaddingException exception)
                        {}
                    }
                }
            }
        }
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();

    }
}
