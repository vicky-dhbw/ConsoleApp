package app;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class AESCryptography {

    public void encrypt(String key, File inputFile, File outputFile) throws IOException{
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToAesCryptographyJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,AESCryptography.class.getClassLoader());

            Class<?> aesClass=Class.forName("AESCryptography",true,urlClassLoader);
            Object aesInstance=aesClass.getMethod("getInstance").invoke(null);
            Object aesPort=aesClass.getDeclaredField("port").get(aesInstance);

            Method encrypt=aesPort.getClass().getMethod("encrypt", String.class,File.class,File.class);
            encrypt.invoke(aesPort,key,inputFile,outputFile);
            System.out.println("file successfully encrypted :)");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void decrypt(String key, File inputFile, File outputFile) throws IOException{
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToAesCryptographyJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,AESCryptography.class.getClassLoader());

            Class<?> aesClass=Class.forName("AESCryptography",true,urlClassLoader);
            Object aesInstance=aesClass.getMethod("getInstance").invoke(null);
            Object aesPort=aesClass.getDeclaredField("port").get(aesInstance);

            Method encrypt=aesPort.getClass().getMethod("decrypt", String.class,File.class,File.class);
            encrypt.invoke(aesPort,key,inputFile,outputFile);
            System.out.println("file successfully decrypted :)");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
