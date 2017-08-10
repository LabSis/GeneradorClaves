/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author paula
 */
public class ControladorClaves {
    private final String alfabeto;
    private final int longitudClave;
    private final String algoritmo;
    private final ClavesArchivo clavesArchivo;
     
   public ControladorClaves(int longitudClave, String algoritmo){
        //alfabeto = "1234567890|’¿qwertyuiop'+asdfghjklñ{}<zxcvbnm,.-°!”#$%&/()=?¡\"*[]>;:_¬\\~^`@QWERTYUIOPASDFGHJKLÑZXCVBNM";
        alfabeto = "0123456789";
        this.longitudClave = longitudClave;
        this.algoritmo = algoritmo;
        
        clavesArchivo = new ClavesArchivo();
    }
    
    public File generarArchivo(){
        clavesArchivo.generarCombinaciones(alfabeto.toCharArray(), longitudClave);
        return clavesArchivo.getFile();
    }
    public String generarHash(String algoritmo, String clave){
        return clavesArchivo.generarHash(algoritmo, clave);
    }
    public String getAlgoritmoUsado(){
        return algoritmo;
    }
    public int getLongitudClave(){
        return longitudClave;
    }
    public String[] generarRT(String algoritmo, String clave){
        return clavesArchivo.generarRT(algoritmo, clave);
    }
    public ArrayList<String> procesoInverso(String hash, String algoritmo){
        return clavesArchivo.procesoInverso(hash, algoritmo);
    }
    public HashMap encontrarHash(String algoritmo, String palabraFinal){
        return clavesArchivo.encontrarHash(algoritmo, palabraFinal);
    }
}
