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
    public static void main(String[] args) throws IOException {
        SistemaUCR system =new SistemaUCNImpl();
        lecturaEstudiantes( system);
        
    }
    
    public static void lecturaEstudiantes(SistemaUCR system)throws IOException{
        System.out.println("Leyendo estudiantes");
        Scanner s = new Scanner(new File("estudiantes.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();            
            String [] partes = line.split(",");
            String rut = partes[0];            
            String correo = partes[1];            
            int nivelAlumno = Integer.parseInt(partes[2]);            
            String constraseña = partes[3];
            try{
                boolean ingreso = system.ingresarAlumno(rut, correo, nivelAlumno, constraseña);
                if(ingreso){
                    line = s.nextLine();
                    int cantAsigCursadas = Integer.parseInt(line);

                    for (int i = 0; i < cantAsigCursadas; i++) {               
                        line = s.nextLine();
                        String [] partes2 = line.split(",");
                        String codigo = partes2[0];
                        
                        Double notaFinal = Double.parseDouble(partes2[1]);
                       
                        try{
                            boolean ingresoAsignaturaCurs = system.ingresarAsociarAlumnoAsignaturaCursada(rut, codigo, notaFinal);
                            if(ingresoAsignaturaCurs){
                                System.out.println("Asignatura Cursada Alumno Ingresada");
                            }else{
                                System.out.println("Asignatura Cursada Alumno NO PUDO ser Ingresada");
                            }
                        }
                       
                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                                
                        }
                    }

                    line = s.nextLine();
                    int cantAsigInscritas = Integer.parseInt(line);
                    for (int i = 0; i < cantAsigInscritas; i++) {               
                        line = s.nextLine();
                        String [] partes3 = line.split(",");
                        String codigo = partes3[0];
                        
                        int numeroParalelo = Integer.parseInt(partes3[1]);
                        try{
                            boolean ingresoAsignaturaIns = system.ingresarAsociarAlumnoAsignaturaInscrita(rut, codigo, numeroParalelo);
                            if(ingresoAsignaturaIns){
                                System.out.println("Asignatura Cursada Alumno Ingresada");
                            }else{
                                System.out.println("Asignatura Cursada Alumno NO PUDO ser Ingresada");
                            }
                        }
                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        

                     }
                }


            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    
    public static void lecturaAsignatura(SistemaUCR system)throws IOException{
        System.out.println("Leyendo asignaturas");
        Scanner s = new Scanner(new File("asignaturas.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String [] partes = line.split(",");
            String codigoAsig = partes[0];
            String nombreAsig = partes[1];
            int creditosAsig = Integer.parseInt(partes[2]);
            String tipoAsignatura = partes[3];
            System.out.println(line);
            if(tipoAsignatura.equals("obligatoria")){
                try{
                    int nivelMalla = Integer.parseInt(partes[4]);                    
                    int cantAsigPreReq = Integer.parseInt(partes[5]);                    
                    for (int i = 6; i < partes.length; i++) {
                        String codigoAsigPreReq = partes[i];
                        try{
                            boolean ingresoOblig = system.ingresarAsignaturaObligatoria(codigoAsig, nombreAsig, creditosAsig, nivelMalla, cantAsigPreReq);
                            boolean asociarIngreso = system.asociarCodigoAsignaturaObligatoria( codigoAsig, codigoAsigPreReq);
                            if (ingresoOblig && asociarIngreso){
                                System.out.println("Asignatura Obligatoria ingresada");
                            }
                        }catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            else if (tipoAsignatura.equals("opcional")){
                try{
                    int creditosPre = Integer.parseInt(partes[4]);
                    boolean ingresoOpc = system.ingresarAsignaturaOpcional(codigoAsig, nombreAsig, creditosAsig, creditosPre);
                    if (ingresoOpc){
                        System.out.println("Asignatura Opcional ingresada");
                    }
                    
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            
        }  

    }
    
    public static void lecturaProfesor(SistemaUCR system)throws IOException{
        System.out.println("Leyendo profesor");
        Scanner s = new Scanner(new File("profesores.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String [] partes = line.split(",");
            String rut = partes[0];
            String correo = partes[1];
            String contraseña = partes[2];
            int salario = Integer.parseInt(partes[3]);
            try{
                boolean ingresoProfesor = system.ingresarProfesor(rut, correo, contraseña, salario);
                if(ingresoProfesor){
                    System.out.println("Ingreso correcto de Profesor");
                }
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }  

    }
    
    public static void lecturaParalelo(SistemaUCR system)throws IOException{
        System.out.println("Leyendo paralelo");
        Scanner s = new Scanner(new File("paralelos.txt"));
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String [] partes = line.split(",");
            int numeroParalelo = Integer.parseInt(partes[0]);
            String codigoAsig = partes[1];
            String rutProfesor = partes[2];
            
            try{
                boolean ingresoParalelo = system.ingresarParalelo(numeroParalelo);
                
                if(ingresoParalelo){
                    System.out.println("Ingreso correcto de Paralelo");
                    try{
                        boolean asociarParalelo = system.ingresarAsociarParaleoAsignaturaProfesor(numeroParalelo, codigoAsig, rutProfesor);
                        if(asociarParalelo){
                            System.out.println("Asociación de paralelo exitoso");
                        }
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    
                }
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }  

    }
    
}
