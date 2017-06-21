/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Lógica.ControladorPrincipal;
import java.util.Scanner;

/**
 *
 * @author paula
 */
public class Main {
    public static void main(String args[]){
        //faltan validaciones
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Seleccionar longitud claves a generar: ");
        int longitud = scanner.nextInt();
        System.out.print("Seleccionar Hash a trabajar: ");
        String hash = scanner.next();
       
        ControladorPrincipal controlador = new ControladorPrincipal(longitud, hash);
        controlador.guardarClaves();

    }
}