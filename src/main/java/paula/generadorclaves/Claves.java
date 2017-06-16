/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paula.generadorclaves;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paula
 */
public class Claves {
    private String clave;
    private String hash;
    private ArrayList<String> listadoClaves;
    private Map<String, String> hashPorClave;
    
    public Claves(){
        clave = "";
        hash = "";
        listadoClaves = new ArrayList<>();
        hashPorClave = new HashMap<>();
    }
    public ArrayList<String> getListadoClaves(){
        return listadoClaves;
    }
    public Map<String, String> getHashPorClave(){
        return hashPorClave;
    }
    
    /*A partir de aquí los métodos necesarios para generar las claves, y sus respectivos hashes*/
    
    /*  
        Método para generar contraseñas, utilizando técnica de Variación sin repeticiones
        @param alfabeto: caracteres a partir de los cuales voy a generar mi contraseña
        @param longitud: longitud de la contraseña
        @param aux: 
        @param longitudAlfabeto: longitud del alfabeto con el cual estoy trabajando
    
    */
    public void variacion(String[] alfabeto, String act, int longitud, int longitudAlfabeto) {
        longitudAlfabeto = alfabeto.length;       
        if (longitud == 0) {
           listadoClaves.add(act);
            
        } else {
            for (int i = 0; i < longitudAlfabeto; i++) {
                
                variacion(alfabeto, act + alfabeto[i], longitud - 1, longitudAlfabeto);
            }
        }
    }
     /*
        Método que genera un hash de una determinada contraseña
        @param algoritmo: MD5, SHA-1, SHA-256, SHA-384, SHA-512
        @param contraseña: contraseña de la cual quiero obtener el valor de hash
     
     */
    public String generarHash(String algoritmo, String contraseña){
    try {
        
        byte bytesContraseña[] = contraseña.getBytes();
        
        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
        byte[] array = messageDigest.digest(bytesContraseña);
       
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < array.length; ++i) {
          String out = Integer.toHexString((array[i] & 0xFF) | 0x100);
          stringBuilder.append(out.substring(1,3));
        }
        return stringBuilder.toString();
    }
  
    catch (NoSuchAlgorithmException e) {
        StringBuilder exception = new StringBuilder();
        exception.append("El algoritmo seleccionado es ");
        exception.append(algoritmo);
        exception.append(", pero es inválido. ");
       
        return exception.toString();
    }
    }
    /*
        Método que retorna un Hash Map que contiene los valores de las contraseñas y sus respectivos hashes
        HashMap<key, value>: <clave, hash> (para que admita valores duplicados de hash, en caso de haberlos)
    */
    public Map<String, String> hashParaCadaClave(){
       
       int size = listadoClaves.size();

       for (int i = 0; i < size; i++) {
        clave = listadoClaves.get(i);
        hash = generarHash("MD5", clave);

        hashPorClave.put(clave, hash);
       }
      
       return hashPorClave;
    }
    
   
}
