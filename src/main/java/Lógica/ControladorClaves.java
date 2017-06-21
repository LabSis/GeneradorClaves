/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.Map;

/**
 *
 * @author paula
 */
public class ControladorClaves {
    private final String auxiliar;
    private final String[] alfabeto;
    private final int longitudAlfabeto;
    private final int longitudClave;
    
    private final String algoritmo;
    
    private final Claves claves;
    private Map<String, String> hashParaCadaClave;
    
    
    public ControladorClaves(int longitudClave, String algoritmo){
        auxiliar = "|1234567890’¿qwertyuiop'+asdfghjklñ{}<zxcvbnm,.-°!”#$%&/()=?¡\"*[]>;:_¬\\~^`@QWERTYUIOPASDFGHJKLÑZXCVBNM ";
        alfabeto = auxiliar.split("");
        longitudAlfabeto = alfabeto.length;
        this.longitudClave = longitudClave;
        this.algoritmo = algoritmo;
        
        claves = new Claves();
    }
    public Map<String, String> generarClaves(){
        claves.variacion(alfabeto, "" , longitudClave, longitudAlfabeto);
       
        hashParaCadaClave = claves.hashParaCadaClave(algoritmo); //este es el hashmap que tendría que guardar
        return hashParaCadaClave;
    }
  
    public int getLongitudClave(){
        return longitudClave;
    }
    public String getAlgoritmoUsado(){
        return algoritmo;
    }
    
    
}
