/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.regex.Pattern;

/**
 *
 * @author paula
 */
public class Topologías {
    /*
        Cadenas planas
    */
    private static final String TOPOLOGIA_1 = "";
    /*
        Empieza con una o más minúsculas
        Seguido por 0 o más mayúsculas
        Seguido por 0 o más digitos numéricos
        
            l*d*s*      bb22..
            l*d*u*      bb22BB #revisar qué tan frecuente es. 
            l*d*l*d*    bb22bb22
            l*s*        bb..
            l*s*d       bb..22
            l*s*l*s*    bb..bb..
            l*u*d*      bbBB22 //la detecta
            l*u*l*u     bbBBbbBB
        
    */
    private static final String TOPOLOGIA_2 = "^[a-z]+[A-Z]*[0-9]*$";
    /*
        Empieza con una o más mayúsculas
        Seguido por 0 o más minúsculas
        Seguido por 0 o más digitos numéricos
           
            u*d*        BB22 //la detecta
            u*s*        BB..
            u*l*u*l*    BBbbBBbb
            u*l*d*      BBbb22 //la detecta
            u*l*s*      BBbb..
            u*l*s*d*    BBbb..22
    */
    private static final String TOPOLOGIA_3 = "^[A-Z]+[a-z]*[0-9]*$";
    /*
        Tipo 4: #ESTA LA IMPLEMENTO MÁS TARDE PORQUE NO ES TAN COMÚN
        d*d*l*      22bb
        d*u*        22BB
        d*u*l*      22BBll
        d*s*d*s*d*  22..22..22
        d*s*d*s*d*l*s*l*s*l*    22..22..bb..bb..bb
        d*l*u*                  22bbBB
        d*l*d*                  22bb22
    */
    private static final String TOPOLOGIA_4 = "";
    
    private static final String PRUEBA = "^([0-9]+)$";
    
    public boolean esTopologiaComun(String contraseña){
       
        Pattern p1 = Pattern.compile(TOPOLOGIA_1);
        Pattern p2 = Pattern.compile(TOPOLOGIA_2);
        Pattern p3 = Pattern.compile(TOPOLOGIA_3);
        Pattern p4 = Pattern.compile(TOPOLOGIA_4);

        return p1.matcher(contraseña).find() || p2.matcher(contraseña).find() || p3.matcher(contraseña).find();
   
    }
}
