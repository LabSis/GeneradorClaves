/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author paula
 */
public class ClavesArchivo {
    private static File archivoClaves;
    private static Topologías t;   
    
    public ClavesArchivo(){
        archivoClaves = new File("claves.txt");
        t = new Topologías();
    }
    public File getFile(){
        return archivoClaves;
    }
    /*
        @param alfabeto: charset a utilizar
        @param longitud: longitud de la clave
    */
    public  void generarCombinaciones(char[] alfabeto, int longitud){
        List<String> combinaciones = new LinkedList<>();
        int indice = 0;
        int contador = 0;
        
        StringBuilder buffer = new StringBuilder();
        String contraseña;
        int indicesAlfabeto[] = new int[longitud];
        while(indice < longitud){
            for(int i = indicesAlfabeto[indice]; i < alfabeto.length; i++, indicesAlfabeto[indice]++){
                char caracter = alfabeto[i];
                buffer.append(caracter);
                contraseña = buffer.toString();
               
                    if(indice == longitud - 1 ){
                        if (t.esTopologiaComun(contraseña)) {
                              combinaciones.add(contraseña);
                              contador++;
                        }
                   
                     buffer.setLength(buffer.length() - 1);
                  
                      if(contador % 100000 == 0){
                        // Cada 100000 generadas escribo en disco
                        escribir(combinaciones);
                        combinaciones.clear();
                                               
                        }
 
                    } else {
                        indicesAlfabeto[indice]++;
                        break;
                    }
                
                

            }
            // A la salida de este for tenemos 3 posibilidades.
            if(indice < longitud - 1 && indicesAlfabeto[indice] < alfabeto.length){
                indice++;
            }
            
            if(indice < longitud - 1 && indicesAlfabeto[indice] == alfabeto.length){
                indice++;
            }
            if(indice == longitud - 1 && indicesAlfabeto[indice] == alfabeto.length){
                while(indicesAlfabeto[indice] == alfabeto.length){
                    if(buffer.length() == 0){
                        indice = longitud;
                        break;
                    }
                    buffer.setLength(buffer.length() - 1);
                    indicesAlfabeto[indice] = 0;
                    indice--;
                }
            }
        }
        escribir(combinaciones);
    }
    
    public void escribir(List<String> lista){
        try(FileWriter fw = new FileWriter(archivoClaves, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            lista.forEach((l) -> {
                out.println(l);

            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
        Método que genera un hash de una determinada clave
    
        @param algoritmo: MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512
        @param clave: clave de la cual quiero obtener el valor de hash
    
    */
    public String generarHash(String algoritmo, String clave){
        try {

            byte bytesContraseña[] = clave.getBytes();

            MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
            byte[] array = messageDigest.digest(bytesContraseña);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < array.length; ++i) {
              String out = Integer.toHexString((array[i] & 0xFF) | 0x100);
              stringBuilder.append(out.substring(1,3));
            }
            return stringBuilder.toString(); //devuelve el valor de hash que le corresponde a la clave
        }

        catch (NoSuchAlgorithmException e) {
            StringBuilder exception = new StringBuilder();
            exception.append("Algoritmo inválido ");
            exception.append(e.getLocalizedMessage());

            return exception.toString();
        }
    }
    /*
        Algoritmo de reducción utilizado para crear la RT
    */
    public void reduccion(){
    
    }
    /*
      Generar RT  
    */
    public void generarRT(){

}
}
