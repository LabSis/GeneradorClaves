/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.File;

/**
 *
 * @author paula
 */
public class ControladorClaves {
    private String alfabeto;
    private int longitudClave;
    private String algoritmo;
    private final Claves clavesArchivo;
     
   public ControladorClaves(){
       clavesArchivo = new Claves();
   
   }
    public ControladorClaves(int longitudClave, String algoritmo){
        alfabeto = "1234567890|’¿qwertyuiop'+asdfghjklñ{}<zxcvbnm,.-°!”#$%&/()=?¡\"*[]>;:_¬\\~^`@QWERTYUIOPASDFGHJKLÑZXCVBNM";
        this.longitudClave = longitudClave;
        this.algoritmo = algoritmo;
        
        clavesArchivo = new Claves();
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
    public String[] getDuplas(String algoritmo, String clave){
        return clavesArchivo.generarDuplas(algoritmo, clave);
    }
    public String getReduccion(String hash){
        return clavesArchivo.reduccionNumerica(hash);
    }

}
