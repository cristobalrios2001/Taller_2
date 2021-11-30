/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class Taller_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    }
    
    public static void lecturaEstudiantes(SistemaSkinShop system)throws IOException{
       System.out.println("Leyendo estudiantes");
       Scanner s = new Scanner(new File("estudiantes.txt"));
       while(s.hasNextLine()) {
           String line = s.nextLine();
           String [] partes = line.split(",");
           String nombrePersonaje = partes[0];
           String rol = partes[1];
           try {
               boolean ingreso = system.agregarPersonaje(nombrePersonaje, rol);
               if(ingreso) {
                   int cantSkins = Integer.parseInt(partes[2]);
                   int cont = 3;
                   for(int i=0;i<cantSkins;i++) {
                       String nombreSkin = partes[cont];
                       String calidadSkin = partes[cont+1];
                       cont+=2;
                       try {
                           boolean ingresoAsocia = system.asociarPersonajeSkin(nombrePersonaje, rol, nombreSkin, calidadSkin);
                           if(!ingresoAsocia) {
                               System.out.println("No se puede ingresar las skin a la cuenta por que no queda espacio");
                           }
                       }catch(Exception ex) {
                           System.out.println("\t"+ex.getMessage());
                       }
                   }

               }
           }catch (Exception ex) {
               System.out.println("\t"+ex.getMessage());
           }
       }
    }
    
}
